package spring_reactive.com.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import spring_reactive.com.service.impl.ScoreService;

@RestController
@RequestMapping("/liveData")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // SSE endpoint - browser বা client connect করে live score পাবে
    @GetMapping(value = "/live-scores", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamScores() {
        return scoreService.getScoreStream();
    }

    // Manual score update endpoint
    @PostMapping("/update-score")
    public String updateScore(@RequestParam String score) {
        scoreService.publishCore(score);
        return "✅ Score updated: " + score;
    }

}
