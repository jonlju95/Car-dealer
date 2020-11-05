package se.ecutb.car_dealer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    private String id;
    @JsonProperty("Brand")
    @NotEmpty(message = "Brand can't be empty")
    private String brand;
    @JsonProperty("Model")
    @NotEmpty(message = "Model can't be empty")
    private String model;
    @JsonProperty("Year")
    @NotEmpty(message = "Year can't be empty")
    private int year;
    @JsonProperty("Status")
    @NotEmpty(message = "Status can't be empty")
    private String status;
    @JsonProperty("RegNum")
    @NotNull
    private String regNum;
}
