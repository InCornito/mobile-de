package de.mobile.domain.ad;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Document(collection = Ad.Meta.DOCUMENT_NAME)
@CompoundIndexes(
        @CompoundIndex(
                def = "{" +
                        "'" + Ad.Meta.MAKE + "':1," +
                        "'" + Ad.Meta.MODEL + "':1," +
                        "'" + Ad.Meta.CATEGORY + "':1" +
                        "}",
                unique = true)
)
public class Ad {

    @Id
    private String id;
    private AdType adType;

    private String make;
    private String model;
    private String description;
    private Category category;
    private BigDecimal price;

    @NotNull
    private String customerEmail;

    public interface Meta {
        String DOCUMENT_NAME = "ads";
        String MAKE = "make";
        String MODEL = "model";
        String CATEGORY = "category";
    }
}

