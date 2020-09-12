package com.profummy.chocolateservice.web.controller;

import com.profummy.chocolateservice.services.ChocolateService;
import com.profummy.chocolateservice.web.model.ChocolateDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/chocolate")
@RestController
public class ChocolateController {

    private final ChocolateService chocolateService;

    public ChocolateController(ChocolateService chocolateService) {
        this.chocolateService = chocolateService;
    }

    @GetMapping({"/{chocolateId}"})
    public ResponseEntity<ChocolateDto> getChocolateById(@PathVariable("chocolateId") UUID chocolateId) {
        return new ResponseEntity<>(chocolateService.getChocolateById(chocolateId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewChocolate(@Valid @RequestBody ChocolateDto chocolateDto) {

        ChocolateDto savedDto = chocolateService.saveNewChocolate(chocolateDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/chocolate/" + savedDto.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{chocolateId}"})
    public ResponseEntity updateChocolateById(@PathVariable("chocolateId") UUID chocolateId, @Valid @RequestBody ChocolateDto chocolateDto) {
        chocolateService.updateChocolate(chocolateId, chocolateDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{chocolateId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChocolate(@PathVariable("chocolateId") UUID chocolateId) {
        chocolateService.deleteById(chocolateId);
    }


}

