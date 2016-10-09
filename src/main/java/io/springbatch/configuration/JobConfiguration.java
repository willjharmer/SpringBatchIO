package io.springbatch.configuration;

import io.springbatch.reader.StatelessItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willharmer on 09/10/2016.
 */

@Configuration
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public StatelessItemReader statelessItemReader() {
        List<String> data = new ArrayList<>(3);

        data.add("foo");
        data.add("bar");
        data.add("baz");

        return new StatelessItemReader(data);
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(2)
                .reader(statelessItemReader())
                .writer(list -> {
                    for (String curItem : list){
                        System.out.println("curItem = " + curItem);
                    }
                }).build();
    }

    @Bean
    public Job interfacesJob(){
        return jobBuilderFactory.get("interfacesJob")
                .start(step1())
                .build();
    }
}
