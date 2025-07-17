package spring_reactive.com.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerStreamController {

    @GetMapping(value = "/stream-customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, Object>> streamCustomers() {
        List<String> names = List.of("Alice", "Bob", "Charlie","abd","bcd","cde","kfl","ijk","lmn","iop","olnj");

        return Flux.fromIterable(names)
                .delayElements(Duration.ofSeconds(1))
                .map(name -> Map.of("name", name, "time", LocalDateTime.now().toString()));
    }

    @GetMapping(value = "/imperativeStyle",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public List<String> ImperativeStream() throws InterruptedException {
        Thread.sleep(5000);

        List<String> impeStrings =   List.of("Alice", "Bob", "Charlie","abd","bcd","cde","kfl","ijk","lmn","iop","olnj");

        Thread.sleep(2000);

        return impeStrings;
    }

}
