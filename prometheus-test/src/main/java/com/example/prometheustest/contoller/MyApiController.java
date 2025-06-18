package com.example.prometheustest.contoller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class 		:MyApiController
 * Package		:com.example.prometheustest.contoller
 * <p>
 * Description:
 *
 * @author: 김동율
 * @since: 25. 6. 2. 오전 9:53
 * @version: 변경이력:
 * 이름     : 일자          : 근거자료   : 변경내용
 * ------------------------------------------------------
 * : 2025. 2. 6. :            : 최초생성
 */

@RestController
public class MyApiController {

    private final MeterRegistry registry;

    public MyApiController(MeterRegistry registry) {
        this.registry = registry;
    }

    @GetMapping("/api/slow")
    public String slowApi() throws InterruptedException {
        Timer.Sample sample = Timer.start(registry);
        try {
            Thread.sleep(1000); // 일부러 느린 API
            return "slow!";
        } finally {
            sample.stop(Timer.builder("api.response.time")
                    .description("API 응답 시간")
                    .tag("endpoint", "/api/slow")
                    .register(registry));
        }
    }
}
