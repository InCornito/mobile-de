package de.mobile.service.impl;

import de.mobile.controller.dto.ad.AdDto;
import de.mobile.controller.dto.ad.AdDtoType;
import de.mobile.controller.dto.ad.CategoryDto;
import de.mobile.controller.dto.ad.MobileAdDto;
import de.mobile.domain.ad.Ad;
import de.mobile.domain.ad.AdType;
import de.mobile.domain.ad.Category;
import de.mobile.mapper.AdMapper;
import de.mobile.repository.AdRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;

@ExtendWith(MockitoExtension.class)
class DefaultAdServiceTest {

    @Mock
    private AdRepository adRepository;

    @Spy
    private AdMapper adMapper = Mappers.getMapper(AdMapper.class);

    @InjectMocks
    private DefaultAdService defaultAdService;

    @Nested
    class AdListTest {

        @DisplayName("Should return empty list when no data")
        @Test
        void shouldReturnEmptyListWhenNoData() {
            //given
            Mockito.when(adRepository.findAll(Mockito.any(PageRequest.class)))
                    .thenReturn(new PageImpl<>(emptyList()));

            //when
            List<AdDto> list = defaultAdService.list(new PageRequest(0, 10));

            //then
            Assert.assertNotNull(list);
            Assert.assertTrue(list.isEmpty());
        }
    }

    @Nested
    class AdGetTest {
        @Test
        void shouldGetItemWhenIdExists() {
            //given
            Ad testAd = new Ad();
            testAd.setMake("Make 1");
            testAd.setCustomerEmail("aaa@xxx.zzz");
            testAd.setCategory(Category.CAR);
            testAd.setDescription("description");
            testAd.setModel("Model 1");
            testAd.setPrice(BigDecimal.valueOf(77777.77));
            testAd.setAdType(AdType.MOBILE);

            Mockito.when(adRepository.findOne(Mockito.any(String.class))).thenReturn(testAd);

            //when
            AdDto adDto = defaultAdService.get("1");

            //then
            Assert.assertNotNull(adDto);
            Assert.assertEquals(testAd.getAdType().name(), adDto.getAdDtoType().name());
            Assert.assertEquals(testAd.getCategory().name(), adDto.getCategoryDto().name());
            Assert.assertEquals(testAd.getDescription(), adDto.getDescription());
            Assert.assertEquals(testAd.getPrice(), adDto.getPrice());
            Assert.assertEquals(testAd.getMake(), adDto.getMake());
            Assert.assertEquals(testAd.getModel(), adDto.getModel());
            Assert.assertEquals(testAd.getCustomerEmail(), adDto.getCustomerEmail());
            Assert.assertEquals(testAd.getId(), adDto.getId());
        }
    }

    @Nested
    class AdCreateTest {
        @Test
        void shouldCreateItem() {
            //given
            Authentication authentication = Mockito.mock(Authentication.class);
            Mockito.when(authentication.getPrincipal()).thenReturn("aaa@xxx.zzz");
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);

            MobileAdDto adDtoTest = new MobileAdDto();
            adDtoTest.setMake("Make 1");
            adDtoTest.setCustomerEmail("aaa@xxx.zzz");
            adDtoTest.setCategoryDto(CategoryDto.CAR);
            adDtoTest.setDescription("description");
            adDtoTest.setModel("Model 1");
            adDtoTest.setPrice(BigDecimal.valueOf(77777.77));
            adDtoTest.setAdDtoType(AdDtoType.MOBILE);

            Ad testAd = new Ad();
            testAd.setMake("Make 1");
            testAd.setCustomerEmail("aaa@xxx.zzz");
            testAd.setCategory(Category.CAR);
            testAd.setDescription("description");
            testAd.setModel("Model 1");
            testAd.setPrice(BigDecimal.valueOf(77777.77));
            testAd.setAdType(AdType.MOBILE);

            Mockito.when(adRepository.save(Mockito.any(Ad.class))).thenReturn(testAd);

            //when
            AdDto adDto = defaultAdService.create(adDtoTest);

            //then
            Assert.assertNotNull(adDto);
            Assert.assertEquals(testAd.getAdType().name(), adDto.getAdDtoType().name());
            Assert.assertEquals(testAd.getCategory().name(), adDto.getCategoryDto().name());
            Assert.assertEquals(testAd.getDescription(), adDto.getDescription());
            Assert.assertEquals(testAd.getPrice(), adDto.getPrice());
            Assert.assertEquals(testAd.getMake(), adDto.getMake());
            Assert.assertEquals(testAd.getModel(), adDto.getModel());
            Assert.assertEquals(testAd.getCustomerEmail(), adDto.getCustomerEmail());
            Assert.assertEquals(testAd.getId(), adDto.getId());
        }
    }
}