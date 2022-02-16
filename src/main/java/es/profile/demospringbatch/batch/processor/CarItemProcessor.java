package es.profile.demospringbatch.batch.processor;

import es.profile.demospringbatch.dto.CarDto;
import es.profile.demospringbatch.model.CarEntity;
import org.springframework.batch.item.ItemProcessor;

import java.util.UUID;

public class CarItemProcessor implements ItemProcessor<CarDto, CarEntity> {
    @Override
    public CarEntity process(CarDto carDto) throws Exception {
        return CarEntity.builder()
                .id(UUID.randomUUID().toString())
                .colour(carDto.getColour())
                .model(carDto.getModel())
                .fuelType(carDto.getFuelType())
                .registration(carDto.getRegistration())
                .build();
    }
}
