package org.jeongmo.plansync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlanSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanSyncApplication.class, args);
    }

}
