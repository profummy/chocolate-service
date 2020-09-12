package com.profummy.chocolateservice.web.model.V2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChocolateDtoV2 {

    private UUID id;
    private Integer version;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    private String chocolateName;
    private ChocolateTypeEnum chocolateType;
    private Long barcode;
    private BigDecimal price;
    private Integer quantityOnHand;
}
