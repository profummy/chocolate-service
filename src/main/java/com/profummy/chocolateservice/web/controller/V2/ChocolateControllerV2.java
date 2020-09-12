package com.profummy.chocolateservice.web.controller.V2;

import com.profummy.chocolateservice.services.V2.ChocolateServiceV2;
import com.profummy.chocolateservice.web.model.V2.ChocolateDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequestMapping("/api/v2/chocolate")
@RestController
public class ChocolateControllerV2 {

    private final ChocolateServiceV2 chocolateServiceV2;

    public ChocolateControllerV2(ChocolateServiceV2 chocolateServiceV2) {
        this.chocolateServiceV2 = chocolateServiceV2;
    }

    @GetMapping({"/{chocolateId}"})
    public ResponseEntity<ChocolateDtoV2> getChocolateById(@PathVariable("chocolateId") UUID chocolateId) {
        return new ResponseEntity<>(chocolateServiceV2.getChocolateById(chocolateId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody ChocolateDtoV2 chocolateDto) {

        ChocolateDtoV2 savedDto = chocolateServiceV2.saveNewChocolate(chocolateDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v2/chocolate/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{chocolateId}"})
    public ResponseEntity handleUpdate(@PathVariable("chocolateId") UUID chocolateId, @Valid @RequestBody ChocolateDtoV2 chocolateDto) {
        chocolateServiceV2.updateChocolate(chocolateId, chocolateDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{chocolateId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChocolate(@PathVariable("chocolateId") UUID chocolateId) {
        chocolateServiceV2.deleteById(chocolateId);
    }



}

