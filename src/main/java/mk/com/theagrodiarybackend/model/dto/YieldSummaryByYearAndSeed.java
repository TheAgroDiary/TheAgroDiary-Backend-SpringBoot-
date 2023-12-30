package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YieldSummaryByYearAndSeed {

    private Integer year;
    private String seedName;
    private Double totalAmountKg;

}
