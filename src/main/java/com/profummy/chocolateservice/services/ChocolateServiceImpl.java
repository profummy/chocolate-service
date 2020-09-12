package com.profummy.chocolateservice.services;

import com.profummy.chocolateservice.web.model.ChocolateDto;
import com.profummy.chocolateservice.web.model.V2.ChocolateTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ChocolateServiceImpl implements ChocolateService{
    @Override
    public ChocolateDto getChocolateById(UUID chocolateId) {
        return ChocolateDto.builder()
                .id(UUID.randomUUID())
                .chocolateName("Ferrero")
                .chocolateType("Nutty")
                .build();
    }

    @Override
    public ChocolateDto saveNewChocolate(ChocolateDto chocolateDto) {
        return ChocolateDto
                .builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateChocolate(UUID chocolateId, ChocolateDto chocolateDto) {

    }

    @Override
    public void deleteById(UUID chocolateId) {
        log.debug("Deleting chocolate...");

    }
}
