package de.mobile.service.impl;

import de.mobile.controller.dto.ad.AdDto;
import de.mobile.domain.ad.Ad;
import de.mobile.domain.ad.exception.AdNotFoundException;
import de.mobile.mapper.AdMapper;
import de.mobile.repository.AdRepository;
import de.mobile.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DefaultAdService implements AdService {

    private final AdRepository adRepository;
    private final AdMapper adMapper;

    @Override
    public AdDto create(AdDto adDtoData) {
        Ad ad = inbound(adDtoData);
        Ad saved = adRepository.save(ad);
        return outbound(saved);
    }

    @Override
    public AdDto get(String adId) {
        Ad ad = adRepository.findOne(adId);
        if (ad == null) {
            throw new AdNotFoundException(String.format("Ad with id = '%s' not found", adId));
        }
        return outbound(ad);
    }

    @Override
    public List<AdDto> list(Pageable pageable) {
        return StreamSupport.stream(
                adRepository.findAll(pageable).spliterator(),
                false
        )
                .map(this::outbound)
                .collect(Collectors.toList());
    }

    private AdDto outbound(Ad ad) {
        return adMapper.map(ad);
    }

    private Ad inbound(AdDto adDto) {
        return adMapper.map(adDto);
    }
}
