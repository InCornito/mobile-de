package de.mobile.service;

import de.mobile.model.dto.ad.AdDto;

import java.util.List;

public interface AdService {

    Long create(AdDto adDtoData); // TODO

    AdDto get(Long adId);

    List<AdDto> list();
}
