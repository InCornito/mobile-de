package de.mobile.service;

import de.mobile.controller.dto.ad.AdDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdService {

    AdDto create(AdDto adDtoData);

    AdDto get(String adId);

    List<AdDto> list(Pageable pageable);
}
