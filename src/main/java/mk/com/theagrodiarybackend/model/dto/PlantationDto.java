package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlantationDto {

    private Float amountKg;
    private String type;
    private Integer year;
    private Integer personId;
    private Integer seedId;
}
