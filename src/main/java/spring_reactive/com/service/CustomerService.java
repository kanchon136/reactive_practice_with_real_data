package spring_reactive.com.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring_reactive.com.dto.CustomerDto;
import spring_reactive.com.dto.paginationDto.PaginationResponseDto;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    Mono<CustomerDto> saveCustomerWithAddress(CustomerDto dto);
    Flux<CustomerDto> saveAllCustomerWithAddresses(List<CustomerDto> dtoList);
    Mono<CustomerDto> getCustomerWithAddress(Long customerId);

    Flux<CustomerDto> findAllCustomers(Long backPresure);
    Flux<CustomerDto> findAll();

    Mono<PaginationResponseDto<CustomerDto>> findAllWithPagination(int page, int size);

    Flux<Map<String,Object>> findAllWithPaginationStream(int page, int size);
}
