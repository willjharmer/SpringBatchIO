package io.springbatch.configuration;

import io.springbatch.domain.Customer;
import io.springbatch.domain.CustomerRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * Created by willharmer on 09/10/2016.
 */

@Configuration
public class JobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Customer> cursorItemReader() {
        JdbcCursorItemReader<Customer> reader = new JdbcCursorItemReader<>();

        reader.setSql("select id, firstname, lastname, birthdate from customer order by lastname, firstname");
        reader.setDataSource(this.dataSource);
        reader.setRowMapper(new CustomerRowMapper());
        return reader;
    }

    @Bean
    public ItemWriter<Customer> customerItemWriter(){
        return items -> {
            for (Customer item : items) {
                System.out.println(item.toString());
            }
        };
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(cursorItemReader())
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Job interfacesJob(){
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }
}
