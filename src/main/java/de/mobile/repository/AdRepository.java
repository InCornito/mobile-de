package de.mobile.repository;

import de.mobile.model.dto.ad.MobileAdDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class AdRepository {

    public Long create(MobileAdDto ad) {
        throw new RuntimeException("not implemented yet");
    }

    public MobileAdDto get(Long adId) {
        throw new RuntimeException("not implemented yet");
    }

    public List<MobileAdDto> list() {
        return new ArrayList<>();
    }

}
