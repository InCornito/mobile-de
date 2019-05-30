package de.mobile.controller.dto.ad;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = AdDto.Meta.AD_DTO_TYPE,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MobileAdDto.class, name = MobileAdDto.Meta.TYPE)
})
@Getter
@Setter
@Validated
public class AdDto {

    private String id;
    @JsonProperty(value = "adType")
    private AdDtoType adDtoType;

    @NotBlank(message = "{make.not.blank}")
    private String make;
    @NotBlank // message omit for test purpose
    private String model;
    private String description;

    @JsonProperty(value = "category")
    @NotNull
    private CategoryDto categoryDto;

    @DecimalMin(value = "0.0")
    private BigDecimal price;

    public interface Meta {
        String AD_DTO_TYPE = "adType";
    }
}
