package io.springbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> this is step1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> this is step2");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> this is step3");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job transitionNextSimpleJob(){
        return jobBuilderFactory.get("transitionJobNext")
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

//    @Bean
//    public Job transitionNextManualJob(){
//        return jobBuilderFactory.get("transitionJobNext")
//                .start(step1())
//                .on("COMPLETE").to(step2())
//                .from(step2()).on("COMPLETE").to(step3())
//                .from(step3()).end()
//                .build();
//    }
}
