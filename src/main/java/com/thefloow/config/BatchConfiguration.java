package com.thefloow.config;

import com.mongodb.Mongo;
import com.thefloow.model.Page;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
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

    @Value("${data.input}")
    private Resource[] dataInput;

    @Value("${spring.data.mongodb.database}")
    private String mongoDb;

    @Autowired
    private Mongo mongo;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public MultiResourceItemReader<Page> multiResourceItemReader(){
        MultiResourceItemReader<Page> reader = new MultiResourceItemReader<>();

        reader.setDelegate(pageItemReader());
        reader.setResources(dataInput);

        return reader;
    }

    @Bean
    public StaxEventItemReader<Page> pageItemReader(){

        XStreamMarshaller unmarshaller = new XStreamMarshaller();

        Map<String, Class> aliases = new HashMap<>();
        aliases.put("page", Page.class);

        unmarshaller.setAliases(aliases);

        StaxEventItemReader reader = new StaxEventItemReader();
        reader.setFragmentRootElementName("page");
        reader.setUnmarshaller(unmarshaller);

        return reader;
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
    public Step stepOne() throws Exception{

        return stepBuilderFactory.get("stepOne")
                .<Page, Page>chunk(100000)
                .reader(multiResourceItemReader())
                .writer(pageItemWriter())
                .taskExecutor(new ThreadPoolTaskExecutor())
                .build();
    }

    @Bean
    public Job batchJob() throws Exception{

        return jobBuilderFactory.get("batchJob")
                                .start(stepOne())
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