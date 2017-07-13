package com.thefloow.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by samif on 13/07/2017.
 */
@Configuration
public class SparkConfiguration {

    @Value("${master.uri}")
    private String masterUri;

    @Value("${spring.application.name}")
    private String application;

    @Bean
    public SparkConf sparkConf(){
        SparkConf sparkConf = new SparkConf().setAppName(application)
                                             .setMaster(masterUri);

        return sparkConf;
    }

    @Bean
    public SparkSession sparkSession(){
        return SparkSession.builder()
                           .sparkContext(sparkContext().sc())
                           .appName("Spring Batch with Spark")
                           .getOrCreate();
    }

    @Bean
    public JavaSparkContext sparkContext(){
        return new JavaSparkContext(sparkConf());
    }
}