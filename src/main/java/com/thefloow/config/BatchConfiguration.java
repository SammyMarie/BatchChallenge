package com.thefloow.config;

import com.thefloow.model.Page;
import lombok.Builder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
    public ItemWriter<Page> pageItemWriter() {
        return items -> {
            for(Page item : items){
                System.out.println(item.toString());
            }
        };
    }

    @Bean
    public Step stepOne(){

        return stepBuilderFactory.get("stepOne")
                .<Page, Page>chunk(100000)
                .reader(pageItemReader())
                .writer(pageItemWriter())
                .build();
    }

    @Bean
    public Job transitionJob(){

        return jobBuilderFactory.get("transitionJob")
                                .start(stepOne())
                                .build();
    }
}