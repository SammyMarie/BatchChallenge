package com.thefloow.config;

import com.mongodb.Mongo;
import com.thefloow.model.Mediawiki;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sammymarie on 04/07/17.
 */
@Configuration
@EnableBatchProcessing
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
    public MultiResourceItemReader<Mediawiki> multiResourceItemReader(){
        MultiResourceItemReader<Mediawiki> reader = new MultiResourceItemReader<>();

        reader.setDelegate(pageItemReader());
        reader.setResources(dataInput);

        return reader;
    }

    @Bean
    public StaxEventItemReader<Mediawiki> pageItemReader(){

        XStreamMarshaller unmarshaller = new XStreamMarshaller();

        Map<String, Class> aliases = new HashMap<>();
        aliases.put("mediawiki", Mediawiki.class);

        unmarshaller.setAliases(aliases);

        StaxEventItemReader reader = new StaxEventItemReader();
        reader.setFragmentRootElementName("mediawiki");
        reader.setUnmarshaller(unmarshaller);

        return reader;
    }

    @Bean
    public MongoItemWriter<Mediawiki> pageItemWriter() throws Exception {
        MongoItemWriter<Mediawiki> itemWriter = new MongoItemWriter<>();

        itemWriter.setCollection("customers");
        itemWriter.setTemplate(mongoTemplate(mongo));
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    @Bean
    public Step stepOne() throws Exception{

        return stepBuilderFactory.get("stepOne")
                .<Mediawiki, Mediawiki>chunk(100000)
                .reader(pageItemReader())
                .writer(pageItemWriter())
                .build();
    }

    @Bean
    public Job transitionJob() throws Exception{

        return jobBuilderFactory.get("transitionJob")
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