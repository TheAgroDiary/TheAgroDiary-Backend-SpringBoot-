package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class ExpenseDto {

    private Float expenseSum;
    private Date date;
    private Float seedAmountKg;
    private String description;
    private Integer personId;
    private Integer seedId;
    private Integer categoryId;
}
