package de.mobile.service.impl;

import de.mobile.model.dto.ad.AdDto;
import de.mobile.repository.AdRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DefaultAdServiceTest {

    @Mock
    private AdRepository adRepository;

    @InjectMocks
    private DefaultAdService defaultAdService;

    @Nested
    class AdListTest {

        @DisplayName("Should return empty list when no data")
        @Test
        void shouldReturnEmptyListWhenNoData() {
            //given
            Mockito.when(adRepository.list()).thenReturn(Collections.emptyList());

            //when
            List<AdDto> list = defaultAdService.list();

            //then
            Assert.assertNotNull(list);
            Assert.assertTrue(list.isEmpty());
        }
    }

    @Nested
    class AdGetTest {
        @Test
        void shouldCreateWhenValidInput() {

        }
    }
}