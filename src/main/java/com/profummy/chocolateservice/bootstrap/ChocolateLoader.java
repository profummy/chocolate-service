package com.profummy.chocolateservice.bootstrap;

import com.profummy.chocolateservice.domain.Chocolate;
import com.profummy.chocolateservice.repositories.ChocolateRepository;
import com.profummy.chocolateservice.web.model.ChocolateTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class ChocolateLoader implements CommandLineRunner {

    private final ChocolateRepository chocolateRepository;

    @Override
    public void run(String... args) throws Exception {

        if(chocolateRepository.count() ==0){
            loadChocolateObjects();
        }

    }

    private void loadChocolateObjects() {

        Chocolate c1 = Chocolate.builder()
            .chocolateName("Galaxy")
            .chocolateType(ChocolateTypeEnum.MILK.toString())
            .quantityToMake(500)
            .minOnHand(10)
            .barcode(333444445988L)
            .price(new BigDecimal("3.50"))
            .build();

           Chocolate c2 = Chocolate.builder()
                    .chocolateName("Gob")
                    .chocolateType(ChocolateTypeEnum.DARK.toString())
                    .quantityToMake(900)
                    .minOnHand(5)
                    .barcode(365332444538L)
                    .price(new BigDecimal("9.50"))
                    .build();

        chocolateRepository.save(c1);
        chocolateRepository.save(c2);
    }


}
