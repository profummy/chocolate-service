package com.profummy.chocolateservice.services.V2;

import com.profummy.chocolateservice.web.model.V2.ChocolateDtoV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ChocolateServiceImplV2 implements ChocolateServiceV2 {
    @Override
    public ChocolateDtoV2 getChocolateById(UUID chocolateId) {
        return null;
    }

    @Override
    public ChocolateDtoV2 saveNewChocolate(ChocolateDtoV2 chocolateDto) {
        return null;
    }

    @Override
    public void updateChocolate(UUID chocolateId, ChocolateDtoV2 chocolateDto) {

    }

    @Override
    public void deleteById(UUID chocolateId) {
        log.debug("Deleting chocolate...");

    }
}
