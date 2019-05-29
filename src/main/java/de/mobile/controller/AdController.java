package de.mobile.controller;

import de.mobile.controller.dto.ad.AdDto;
import de.mobile.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("ads")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    @PostMapping
    public AdDto create(@RequestBody @Valid @NotNull AdDto adDto) {
        return adService.create(adDto);
    }

    @GetMapping(value = "/{id}")
    public AdDto get(@PathVariable("id") String adId) {
        return adService.get(adId);
    }

    @GetMapping
    public List<AdDto> list(@PageableDefault(sort = {AdDto.Meta.AD_DTO_TYPE}) Pageable pageable) {
        return adService.list(pageable);
    }
}
