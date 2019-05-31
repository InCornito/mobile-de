package de.mobile.mapper;

import de.mobile.controller.dto.ad.AdDto;
import de.mobile.controller.dto.ad.MobileAdDto;
import de.mobile.domain.ad.Ad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface AdMapper {

    @Mappings({
            @Mapping(target = "adDtoType", source = "ad.adType"),
            @Mapping(target = "categoryDto", source = "ad.category")
    })
    MobileAdDto toDto(Ad ad);

    @Mappings({
            @Mapping(target = "adType", source = "mobileAdDto.adDtoType"),
            @Mapping(target = "category", source = "mobileAdDto.categoryDto"),
            @Mapping(ignore = true, target = "id"),
            @Mapping(ignore = true, target = "customerEmail")
    })
    Ad toDomain(MobileAdDto mobileAdDto);

    default AdDto map(Ad ad) {
        if (ad == null) {
            return null; // TODO
        }
        switch (ad.getAdType()) {
            case MOBILE:
                return toDto(ad);
            default:
                throw new IllegalArgumentException("Unexpected enum constant: " + ad.getAdType());
        }
    }

    default Ad map(AdDto adDto) {
        if (adDto == null) {
            return null; // TODO
        }
        switch (adDto.getAdDtoType()) {
            case MOBILE:
                return toDomain((MobileAdDto) adDto);
            default:
                throw new IllegalArgumentException("Unexpected enum constant: " + adDto.getAdDtoType());
        }
    }
}
