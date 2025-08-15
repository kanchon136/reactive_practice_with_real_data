package spring_reactive.com.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
@Service
public class ScoreService {

    private final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();




public Flux<String> getScoreStream(){
      return sink.asFlux();
}


public void publishCore(String score){
    Sinks.EmitResult result = sink.tryEmitNext(score);

    log.info("Result==>"+result);

    if (result.isFailure()){
        log.error("failed  the emmit  result"+result.toString());
    }
}


}
