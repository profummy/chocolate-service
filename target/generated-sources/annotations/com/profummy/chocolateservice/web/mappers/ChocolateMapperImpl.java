package com.profummy.chocolateservice.web.mappers;

import com.profummy.chocolateservice.domain.Chocolate;
import com.profummy.chocolateservice.domain.Chocolate.ChocolateBuilder;
import com.profummy.chocolateservice.web.model.ChocolateDto;
import com.profummy.chocolateservice.web.model.ChocolateDto.ChocolateDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-12T17:21:08+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.3 (Amazon.com Inc.)"
)
@Component
public class ChocolateMapperImpl implements ChocolateMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public ChocolateDto ChocolateToChocolateDto(Chocolate chocolate) {
        if ( chocolate == null ) {
            return null;
        }

        ChocolateDtoBuilder chocolateDto = ChocolateDto.builder();

        chocolateDto.id( chocolate.getId() );
        if ( chocolate.getVersion() != null ) {
            chocolateDto.version( chocolate.getVersion().intValue() );
        }
        chocolateDto.createdDate( dateMapper.asOffsetDateTime( chocolate.getCreatedDate() ) );
        chocolateDto.lastModifiedDate( dateMapper.asOffsetDateTime( chocolate.getLastModifiedDate() ) );
        chocolateDto.chocolateName( chocolate.getChocolateName() );
        chocolateDto.chocolateType( chocolate.getChocolateType() );
        chocolateDto.barcode( chocolate.getBarcode() );
        chocolateDto.price( chocolate.getPrice() );

        return chocolateDto.build();
    }

    @Override
    public Chocolate ChocolateDtoToChocolate(ChocolateDto chocolateDto) {
        if ( chocolateDto == null ) {
            return null;
        }

        ChocolateBuilder chocolate = Chocolate.builder();

        chocolate.id( chocolateDto.getId() );
        if ( chocolateDto.getVersion() != null ) {
            chocolate.version( chocolateDto.getVersion().longValue() );
        }
        chocolate.createdDate( dateMapper.asTimestamp( chocolateDto.getCreatedDate() ) );
        chocolate.lastModifiedDate( dateMapper.asTimestamp( chocolateDto.getLastModifiedDate() ) );
        chocolate.chocolateName( chocolateDto.getChocolateName() );
        chocolate.chocolateType( chocolateDto.getChocolateType() );
        chocolate.barcode( chocolateDto.getBarcode() );
        chocolate.price( chocolateDto.getPrice() );

        return chocolate.build();
    }
}
