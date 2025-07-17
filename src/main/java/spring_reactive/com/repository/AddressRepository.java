package spring_reactive.com.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import spring_reactive.com.entity.Address;

public interface AddressRepository extends ReactiveCrudRepository<Address,Long> {
    Flux<Address> findAddressByCustomerId(Long customerId);
}
