package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class RevenueDto {

    private Float revenueSum;
    private Date date;
    private Float seedAmountKg;
    private Integer personId;
    private Integer seedId;
}
