package com.profummy.chocolateservice.web.model;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChocolateDto {
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
