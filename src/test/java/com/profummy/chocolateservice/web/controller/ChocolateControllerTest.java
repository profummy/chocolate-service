package com.profummy.chocolateservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profummy.chocolateservice.web.model.ChocolateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ChocolateController.class)
class ChocolateControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getChocolateById() throws Exception {
        mockMvc.perform(get("/api/v1/chocolate/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewChocolate() throws Exception {
        ChocolateDto chocolateDto = ChocolateDto.builder().build();
        String chocolateDtoJson = objectMapper.writeValueAsString(chocolateDto);

        mockMvc.perform(post("/api/v1/chocolate/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(chocolateDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateChocolateById() throws Exception {
        ChocolateDto chocolateDto = ChocolateDto.builder().build();

        String chocolateDtoJson = objectMapper.writeValueAsString(chocolateDto);

        mockMvc.perform(put("/api/v1/chocolate/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(chocolateDtoJson))
                .andExpect(status().isNoContent());
    }
}