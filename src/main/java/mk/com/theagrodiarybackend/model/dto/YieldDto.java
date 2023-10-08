package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class YieldDto {

    private Float amountKg;
    private String type;
    private Integer year;
    private Long personId;
    private Long seedId;
}
