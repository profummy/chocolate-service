package com.profummy.chocolateservice.web.mappers;

import com.profummy.chocolateservice.domain.Chocolate;
import com.profummy.chocolateservice.web.model.ChocolateDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface ChocolateMapper {

    ChocolateDto ChocolateToChocolateDto (Chocolate chocolate);

    Chocolate ChocolateDtoToChocolate (ChocolateDto chocolateDto);

}
