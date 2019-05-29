package de.mobile.controller.dto.ad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MobileAdDto extends AdDto {

    public interface Meta {
        String TYPE = "MOBILE";
    }
}
