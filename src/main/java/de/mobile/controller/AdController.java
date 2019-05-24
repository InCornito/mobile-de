package de.mobile.controller;

import de.mobile.model.dto.ad.AdDto;
import de.mobile.service.AdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("ads")
public class AdController {
    
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping(value = "/{id}")
    public AdDto get(@PathVariable("id") Long adId) {
        return adService.get(adId);
    }

    @GetMapping
    public List<AdDto> list() {
        return adService.list();
    }
}
