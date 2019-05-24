package de.mobile.service.impl;

import de.mobile.model.dto.ad.AdDto;
import de.mobile.model.dto.ad.MobileAdDto;
import de.mobile.repository.AdRepository;
import de.mobile.service.AdService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DefaultAdService implements AdService {

    private final AdRepository adRepository;

    public DefaultAdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Long create(AdDto adDtoData) {
        MobileAdDto ad = inbound(adDtoData);
        return adRepository.create(ad);
    }

    @Override
    public AdDto get(Long adId) {
        MobileAdDto mobileAdDto = adRepository.get(adId);
        return outbound(mobileAdDto);
    }

    @Override
    public List<AdDto> list() {
        return adRepository.list() //
                .stream() //
                .map(this::outbound) //
                .collect(Collectors.toList());
    }

    private AdDto outbound(MobileAdDto mobileAdDto) {
        AdDto adDtoData = new AdDto();
        adDtoData.setId(mobileAdDto.getId());
        adDtoData.setCategory(mobileAdDto.getCategory());
        adDtoData.setMake(mobileAdDto.getMake());
        adDtoData.setModel(mobileAdDto.getModel());
        adDtoData.setDescription(mobileAdDto.getDescription());
        adDtoData.setPrice(mobileAdDto.getPrice());
        return adDtoData;
    }

    private MobileAdDto inbound(AdDto adDtoData) {
        MobileAdDto mobileAdDto = new MobileAdDto();
        mobileAdDto.setId(adDtoData.getId());
        mobileAdDto.setCategory(adDtoData.getCategory());
        mobileAdDto.setMake(adDtoData.getMake());
        mobileAdDto.setModel(adDtoData.getModel());
        mobileAdDto.setDescription(adDtoData.getDescription());
        mobileAdDto.setPrice(adDtoData.getPrice());
        return mobileAdDto;
    }
}
