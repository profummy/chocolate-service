package com.profummy.chocolateservice.services;

import com.profummy.chocolateservice.web.model.ChocolateDto;

import java.util.UUID;

public interface ChocolateService {

    ChocolateDto getChocolateById(UUID chocolateId);
    ChocolateDto saveNewChocolate(ChocolateDto chocolateDto);
    void updateChocolate(UUID chocolateId,ChocolateDto chocolateDto);
    void deleteById(UUID chocolateId);

}

