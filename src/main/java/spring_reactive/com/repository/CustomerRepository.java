package spring_reactive.com.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring_reactive.com.entity.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer,Long> {

    @Query("SELECT * FROM customer ORDER BY id DESC")
    Flux<Customer> findAllOrderByIdDesc();

    @Query("SELECT * FROM customer ORDER BY id DESC LIMIT 1")
    Mono<Customer> findTopCustomer();

    @Query("SELECT * FROM customer LIMIT :size OFFSET :offset")
    Flux<Customer> findCustomersByPage(@Param("offset") int offset, @Param("size") int size);

    @Query("SELECT COUNT(*) FROM customer")
    Mono<Long> countAllCustomers();

}
