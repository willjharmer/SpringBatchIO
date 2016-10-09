package io.springbatch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by willharmer on 09/10/2016.
 */

@Configuration
public class FlowLastConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step mystep(){
        return stepBuilderFactory.get("mystep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("this is mystep");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job flowLastJob(Flow flow){
        return jobBuilderFactory.get("flowLastJob")
                .start(mystep())
                .on("COMPLETED").to(flow)
                .end()
                .build();
    }
}
