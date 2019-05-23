package de.mobile.model.dto.ad;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AdDto {
    
    private Long id;
    private String make;
    private String model;
    private String description;
    private Category category;
    private BigDecimal price;
}
