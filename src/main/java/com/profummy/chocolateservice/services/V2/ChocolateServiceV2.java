package com.profummy.chocolateservice.services.V2;

import com.profummy.chocolateservice.web.model.ChocolateDto;
import com.profummy.chocolateservice.web.model.V2.ChocolateDtoV2;

import java.util.UUID;

public interface ChocolateServiceV2 {

    ChocolateDtoV2 getChocolateById(UUID chocolateId);
    ChocolateDtoV2 saveNewChocolate(ChocolateDtoV2 chocolateDto);
    void updateChocolate(UUID chocolateId,ChocolateDtoV2 chocolateDto);
    void deleteById(UUID chocolateId);

}

