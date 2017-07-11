package com.thefloow;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by sammymarie on 04/07/17.
 */
@SpringBootApplication
@EnableBatchProcessing
public class ChallengeApp {

    public static void main(String... args){
        SpringApplication.run(ChallengeApp.class, args);
    }
}