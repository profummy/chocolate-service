package com.profummy.chocolateservice.web.model;

import com.profummy.chocolateservice.web.model.V2.ChocolateTypeEnum;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChocolateDto {
//@Null Read only properties on client side
    @Null
    private UUID id;

    @Null
    private Integer version;

    @Null
    private OffsetDateTime createdDate;

    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String chocolateName;

    @NotNull
    private String chocolateType;

    @Positive
    @NotNull
    private Long barcode;

    @Positive
    @NotNull
    private BigDecimal price;

    private Integer quantityOnHand;
}
