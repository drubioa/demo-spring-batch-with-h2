package es.profile.demospringbatch.config.batch;

import es.profile.demospringbatch.batch.processor.CarItemProcessor;
import es.profile.demospringbatch.dto.CarDto;
import es.profile.demospringbatch.listener.CarJobExecutionListener;
import es.profile.demospringbatch.model.CarEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory ;

    @Bean
    public ItemReader<CarDto> reader() {
        return new FlatFileItemReaderBuilder<CarDto>()
                .name("carItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .linesToSkip(1)
                .delimited()
                .names(new String[]{"registration", "colour","model","fuelType"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CarDto>() {{
                    setTargetType(CarDto.class);
                }})
                .build();
    }

    @Bean
    public CarItemProcessor processor() {
        return new CarItemProcessor();
    }

    @Bean
    public ItemWriter<CarEntity> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<CarEntity>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO cars (id, registration, colour, model, fuelType ) VALUES (:id, :registration, " +
                        ":colour, :model, :fuelType)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job createEmployeeJob(CarJobExecutionListener listener, Step step1) {
        return jobBuilderFactory
                .get("createEmployeeJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(ItemReader<CarDto> reader, ItemWriter<CarEntity> writer,
                      ItemProcessor<CarDto, CarEntity> processor) {
        return stepBuilderFactory
                .get("step1")
                .<CarDto, CarEntity>chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }



}
