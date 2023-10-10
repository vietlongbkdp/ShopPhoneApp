package services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllProductDto {
    private int id;

    private String productName;
    private int quantity;

    private String branchName;

    private String urlImage;

    private BigDecimal price;
}
