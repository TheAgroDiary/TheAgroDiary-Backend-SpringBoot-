package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
public class RevenueDto {

    private Float revenueSum;
    private ZonedDateTime date;
    private Float seedAmountKg;
    private Long personId;
    private Long seedId;
}
