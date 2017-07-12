package com.thefloow.config;

import com.mongodb.Mongo;
import com.thefloow.component.DataRangePartitioner;
import com.thefloow.model.Page;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sammymarie on 04/07/17.
 */
@Configuration
public class BatchConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String mongoDb;

    @Autowired
    private Mongo mongo;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public DataRangePartitioner rangePartitioner(){
        return new DataRangePartitioner();
    }

    @Bean
    @StepScope
    public StaxEventItemReader<Page> pageItemReader(@Value("#{jobParameters['input']}")String dataInput) throws Exception {

        StaxEventItemReader reader = new StaxEventItemReader();
        reader.setResource(new ClassPathResource(dataInput));
        reader.setFragmentRootElementName("page");
        reader.setUnmarshaller(unmarshaller());

        return reader;
    }

    @Bean
    public XStreamMarshaller unmarshaller() {
        XStreamMarshaller unmarshaller = new XStreamMarshaller();

        Map<String, Class> aliases = new HashMap<>();
        aliases.put("page", Page.class);

        unmarshaller.setAliases(aliases);
        return unmarshaller;
    }

    @Bean
    public MongoItemWriter<Page> pageItemWriter() throws Exception {
        MongoItemWriter<Page> itemWriter = new MongoItemWriter<>();

        itemWriter.setCollection("page");
        itemWriter.setTemplate(mongoTemplate(mongo));
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    @Bean
    public Step masterStep() throws Exception{

        return stepBuilderFactory.get("masterStep")
                .partitioner(slaveStep().getName(), rangePartitioner())
                .step(slaveStep())
                .gridSize(8)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(5);
        pool.setMaxPoolSize(10);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }

    @Bean
    public Step slaveStep() throws Exception {

        return stepBuilderFactory.get("slaveStep")
                .<Page, Page>chunk(100000)
                .reader(pageItemReader(null))
                .writer(pageItemWriter())
                .faultTolerant()
                .retry(Exception.class)
                .retryLimit(15)
                .build();
    }

    @Bean
    public Job batchJob() throws Exception{

        return jobBuilderFactory.get("batchJob")
                                .start(masterStep())
                                .build();
    }

    @Bean
    public MongoClientFactoryBean mongoDbFactory(){
        MongoClientFactoryBean mongoClient = new MongoClientFactoryBean();
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo){
        return new MongoTemplate(mongo, mongoDb);
    }
}