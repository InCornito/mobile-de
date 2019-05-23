package de.mobile;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import java.util.List;


@RestController
@RequestMapping("ad")
public class AdResource {
    
    private final AdService adService;

    @Inject
    public AdResource(AdService adService) {
        this.adService = adService;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Ad get(@PathVariable("id") Long adId) {
        return adService.get(adId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Ad> list() {
        return adService.list();
    }

}
