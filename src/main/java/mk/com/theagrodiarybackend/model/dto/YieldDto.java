package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class YieldDto {

    private Float amountKg;
    private String type;
    private Integer year;
    private Date updatedAt;
    private Integer personId;
    private Integer seedId;
}
