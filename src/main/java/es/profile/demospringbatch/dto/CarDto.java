package es.profile.demospringbatch.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class CarDto {
    private String registration;
    private String colour;
    private String model;
    private String fuelType;

    public CarDto() {}
}
