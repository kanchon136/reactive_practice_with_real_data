package spring_reactive.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class ThymleafController {

    @GetMapping("/stream-page")
    public Mono<String> textHomePage(){
        return  Mono.just("home");
    }


}
