package com.thefloow.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sammymarie on 04/07/17.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step stepOne(){
        return stepBuilderFactory.get("stepOne")
                .tasklet((contribution, chunkContext) -> {
                        System.out.println(">> This is step 1");
                        return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step stepTwo(){
        return stepBuilderFactory.get("stepTwo")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> This is step 2");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step stepThree(){
        return stepBuilderFactory.get("stepThree")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> This is step 3");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job transitionJob(){
        return jobBuilderFactory.get("transitionJob")
                .start(stepOne())
                .on("COMPLETED").to(stepTwo())
                .from(stepTwo()).on("COMPLETED").to(stepThree())
                .from(stepThree()).end()
                .build();
    }
}