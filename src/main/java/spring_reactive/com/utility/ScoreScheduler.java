package spring_reactive.com.utility;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import spring_reactive.com.service.impl.ScoreService;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScoreScheduler {

private final ScoreService scoreService;

private final AtomicInteger  overCounter  = new AtomicInteger(1);

    public ScoreScheduler(ScoreService scoreService){
        this.scoreService = scoreService;
    }
    @PostConstruct
    public void startSimulation(){
        Flux.interval(Duration.ofSeconds(5)) // প্রতি 5 সেকেন্ডে update দিবে
                .map(tick -> {
                    int over = overCounter.getAndIncrement();
                    int runs = (int) (Math.random() * 7); // 0-6 runs
                    int wickets = (int) (Math.random() * 2); // 0 বা 1 wicket
                    return "Over " + over + ": " + runs + "/" + wickets;
                })
                .subscribe(scoreService::publishCore);


    }
}
