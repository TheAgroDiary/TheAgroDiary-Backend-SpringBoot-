package mk.com.theagrodiarybackend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YieldSummaryByYearAndSeedAndType {

    private Integer year;
    private String seedName;
    private String type;
    private Double totalAmountKg;

}
