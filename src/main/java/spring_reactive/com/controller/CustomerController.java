package spring_reactive.com.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring_reactive.com.dto.CustomerDto;
import spring_reactive.com.dto.paginationDto.PaginationResponseDto;
import spring_reactive.com.service.CustomerService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/saveCustomer")
    public Mono<ResponseEntity<CustomerDto>> saveCustomer(@RequestBody CustomerDto customerDto){
        return  customerService.saveCustomerWithAddress(customerDto)
                .map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PostMapping("/saveAllCustomer")
    public Mono<ResponseEntity<Flux<CustomerDto>>>  saaveAllCustomer(@RequestBody List<CustomerDto>  customerDtoList){
        Flux<CustomerDto> savedAllCustomerDto = customerService.saveAllCustomerWithAddresses(customerDtoList);
        return Mono.just(ResponseEntity.ok().body(savedAllCustomerDto));
    }

    @GetMapping(value = "/findAll",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //@GetMapping(value = "/findAll",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<ResponseEntity<Flux<CustomerDto>>>  findAllCustomer(){
        Flux<CustomerDto>  customerDtoFlux = customerService.findAll();
        return  Mono.just(ResponseEntity.ok().body(customerDtoFlux));
    }

//    @GetMapping(value = "/findAllStream", produces = MediaType.APPLICATION_NDJSON_VALUE)
//    public ResponseEntity<Flux<CustomerDto>> streamCustomers(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Flux<CustomerDto> stream = customerService.findAllWithPagination(page, size);
//        return ResponseEntity.ok().contentType(MediaType.APPLICATION_NDJSON).body(stream);
//    }


    @GetMapping("/findAllWithPagination")
    public Mono<ResponseEntity<PaginationResponseDto<CustomerDto>>> findAllWithPagination(
            @RequestParam(defaultValue = "0",required = true) int page, @RequestParam(defaultValue = "10") int size){
         return  customerService.findAllWithPagination(page,size).map(responseDto->
                 ResponseEntity.ok().body(responseDto)).defaultIfEmpty(ResponseEntity.noContent().build());

    }

  @GetMapping(value = "/findAllWithPaginationStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public ResponseEntity<Flux<Map<String,Object>>> findAllWithPaginationStream(
          @RequestParam(defaultValue = "0",required = true) int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(customerService.findAllWithPaginationStream(page,size));

  }

    @GetMapping(value = "/findAllWithPaginationStreamNew", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, Object>> findAllWithPaginationStreamNew(@RequestParam(defaultValue = "0",required = true) int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        return customerService.findAllWithPaginationStream(page, size);
    }



}
