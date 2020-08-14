package com.profummy.chocolateservice.web.controller;

import com.profummy.chocolateservice.web.model.ChocolateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/chocolate")
@RestController
public class ChocolateController {

    @GetMapping("/{chocolateId}")
    public ResponseEntity<ChocolateDto> getChocolateById(@PathVariable("chocolateId")UUID chocolateId){
        return new ResponseEntity<>(ChocolateDto.builder().build(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity handlePost( @RequestBody ChocolateDto chocolateDto){
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{chocolateId}")
    public ResponseEntity handleUpdate(@PathVariable("chocolateId") UUID chocolateId, @RequestBody ChocolateDto chocolateDto){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
