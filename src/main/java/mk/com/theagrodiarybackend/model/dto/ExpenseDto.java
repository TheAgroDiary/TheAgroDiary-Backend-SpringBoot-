package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
public class ExpenseDto {

    private Float expenseSum;
    private ZonedDateTime date;
    private Float seedAmountKg;
    private String expenseType;
    private String description;
    private Long personId;
    private Long seedId;
}
