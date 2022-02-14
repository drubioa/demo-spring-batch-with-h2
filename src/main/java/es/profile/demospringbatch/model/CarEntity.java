package es.profile.demospringbatch.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarEntity {
    private String id;
    private String registration;
    private String colour;
    private String model;
    private String fuelType;
}
