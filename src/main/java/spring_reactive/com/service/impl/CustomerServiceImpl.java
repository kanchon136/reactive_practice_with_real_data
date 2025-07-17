package spring_reactive.com.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring_reactive.com.dto.CustomerDto;
import spring_reactive.com.dto.paginationDto.PaginationResponseDto;
import spring_reactive.com.entity.Address;
import spring_reactive.com.entity.Customer;
import spring_reactive.com.mapper.EntityAndDtoMapper;
import spring_reactive.com.repository.AddressRepository;
import spring_reactive.com.repository.CustomerRepository;
import spring_reactive.com.service.CustomerService;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Mono<CustomerDto> saveCustomerWithAddress(CustomerDto dto) {


        return customerRepository.save(EntityAndDtoMapper.toCustomerEntity(dto))
                .flatMap(savedCustomer ->
                        addressRepository.saveAll(dto.getAddresses().stream()
                                                .map(add ->
                                                        EntityAndDtoMapper.toAddressEntity(add, savedCustomer.getId()))
                                                .toList()).collectList()
                                .map(savedAddress ->
                                        EntityAndDtoMapper.toCustomerDto(savedCustomer, savedAddress)))
                                .onErrorResume(err -> {
                                    log.error("Failed to save customer or address: {}", err.getMessage(), err);
                                         return Mono.just(EntityAndDtoMapper.toCustomerDto(EntityAndDtoMapper.toCustomerEntity(dto),
                                          Collections.emptyList()));

                });
    }

    @Override
    public Flux<CustomerDto> saveAllCustomerWithAddresses(List<CustomerDto> dtoList) {
        return Flux.fromIterable(dtoList)
                .flatMap(this::saveCustomerWithAddress);
    }

    @Override
    public Mono<CustomerDto> getCustomerWithAddress(Long customerId) {
        Mono<Customer> customerMono = customerRepository.findById(customerId);
        Flux<Address> addressFlux = addressRepository.findAddressByCustomerId(customerId);

        return customerMono.zipWith(addressFlux.collectList())
                .map(tuple -> EntityAndDtoMapper
                        .toCustomerDto(tuple.getT1(), tuple.getT2()));
    }

    @Override
    public Flux<CustomerDto> findAllCustomers(Long backPresure) {

        return customerRepository.findAll().limitRate(backPresure.intValue())
                .flatMap(customer ->
                        addressRepository.findAddressByCustomerId(customer.getId())
                                .collectList().map(addresses ->
                                        EntityAndDtoMapper.toCustomerDto(customer, addresses)));

    }

    @Override
    public Flux<CustomerDto> findAll() {

       // System.out.println("MainThread== "+Thread.currentThread().getName());
        return customerRepository.findAll().delayElements(Duration.ofSeconds(1))
                .doOnSubscribe(s-> System.out.println("ThreadName ="+Thread.currentThread().getName()))
                .doOnNext(s-> System.out.println("process On == "+ Thread.currentThread().getName()))
                .flatMap(customer ->
                        addressRepository.findAddressByCustomerId(customer.getId())
                                .collectList().delayElement(Duration.ofMillis(1000))
                                .map(address ->
                                        EntityAndDtoMapper.toCustomerDto(customer, address))
                                .onErrorResume(e -> {
                                    return Mono.just(EntityAndDtoMapper.toCustomerDto(customer, Collections.emptyList()));
                                })
                );
    }

    @Override
    public Mono<PaginationResponseDto<CustomerDto>> findAllWithPagination(int page, int size) {

        int offset  = page * size;
        Mono<Long> totalMono = customerRepository.countAllCustomers();
        totalMono.doOnNext(s-> System.out.println("Total Count Of Customer =="+s));
        Flux<Customer>  customerPage = customerRepository.findCustomersByPage(offset,size)
                .delayElements(Duration.ofMillis(200));

        Flux<CustomerDto> customerDtoFlux = customerPage.flatMap(customer ->
                addressRepository.findAddressByCustomerId(customer.getId())
                        .collectList().map(address-> EntityAndDtoMapper.toCustomerDto(customer,address))
                        .onErrorResume(e->
                              //  log.error(""+e.getMessage());
                                Mono.just(EntityAndDtoMapper.toCustomerDto(customer,Collections.emptyList())))
        );

        return totalMono.zipWith(
                customerDtoFlux
                        .collectList()
                        .map(dtoList -> {
                            dtoList.sort(Comparator.comparing(CustomerDto::getId));
                            return dtoList;
                        })
        ).map(tuple -> {
            long total = tuple.getT1();
            List<CustomerDto> sortedDtoList = tuple.getT2();
            int totalPages = (int) Math.ceil((double) total / size);
            return new PaginationResponseDto<>(page, size, (int) total, totalPages, sortedDtoList);
        });
    }

    @Override
    public Flux<Map<String, Object>> findAllWithPaginationStream(int page, int size) {

        int offSet = page * size;

        Mono<Long> totalCount = customerRepository.countAllCustomers();

        Flux<Customer> customerFlux = customerRepository.findCustomersByPage(offSet,size).delayElements(Duration.ofMillis(200));

       Flux<Map<String,Object>> mapFlux = customerFlux.flatMap(customer ->
               addressRepository.findAddressByCustomerId(customer.getId()).collectList()
                       .map(adresess-> EntityAndDtoMapper.toCustomerDto(customer,adresess))
                       .onErrorResume(e-> Mono.just(EntityAndDtoMapper.toCustomerDto(customer,Collections.emptyList())))
       ).map(map-> Map.of("customerInfo",map));


       Mono<Map<String,Object>>  metaMono = totalCount.map(total ->{
           int totalPages = (int) Math.ceil((double) total / size);

           return Map.of("pageInfo", Map.of(
                   "page", page,
                   "size", size,
                   "totalElements", total,
                   "totalPages", totalPages
           ));

       });


        return metaMono.concatWith(mapFlux);
    }


//    @Override
//    public Flux<CustomerDto> findAll() {
//        return customerRepository.findAll()
//                .flatMap(customer ->
//                                addressRepository.findAddressByCustomerId(customer.getId())
//                                        .collectList()
//                                        .delayElement(Duration.ofMillis(1000))  // downstream delay
//                                        .map(address ->
//                                                EntityAndDtoMapper.toCustomerDto(customer, address))
//                                        .onErrorResume(e -> Mono.just(EntityAndDtoMapper.toCustomerDto(customer, Collections.emptyList())))
//                        , 5) // <-- concurrency limit 5 diye rakhsi
//                .onBackpressureBuffer(
//                        10, // max buffer size
//                        dropped -> System.out.println("Dropped: " + dropped) // overflow handler
//                     //   BackpressureOverflowStrategy.ERROR // overflow hole error throw korbe
//
//
//}   );



  }
