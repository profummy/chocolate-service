package com.profummy.chocolateservice.repositories;

import com.profummy.chocolateservice.domain.Chocolate;
import com.profummy.chocolateservice.web.model.ChocolateTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ChocolateRepository extends JpaRepository<Chocolate, UUID> {
    Page<Chocolate> findAllByChocolateName(String chocolateName, Pageable pageable);

    Page<Chocolate> findAllByChocolateType(ChocolateTypeEnum chocolateType, Pageable pageable);

    Page<Chocolate> findAllByChocolateNameAndChocolateType(String chocolateName, ChocolateTypeEnum chocolateType, Pageable pageable);

    Chocolate findByBarcode(String barcode);
}
