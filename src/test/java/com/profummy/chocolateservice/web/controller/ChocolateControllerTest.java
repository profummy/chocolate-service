package com.profummy.chocolateservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profummy.chocolateservice.services.ChocolateService;
import com.profummy.chocolateservice.web.model.ChocolateDto;
import com.profummy.chocolateservice.web.model.V2.ChocolateTypeEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean
    ChocolateService chocolateService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private static ChocolateDto validChocolate;

    @BeforeAll
    public static void setUp() {
        validChocolate = ChocolateDto.builder().id(UUID.randomUUID())
                .chocolateName("Galaxy")
                .chocolateType("Dark")
                .barcode(123456789L)
                .build();
    }

    @Test
    void getChocolate() throws Exception {

        given(chocolateService.getChocolateById(any(UUID.class))).willReturn(validChocolate);

        mockMvc.perform(get("/api/v1/chocolate/" + validChocolate.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validChocolate.getId().toString())))
                .andExpect(jsonPath("$.chocolateName", is("Galaxy")));

    }

    @Test
    void handlePost() throws Exception {
        ChocolateDto chocolateDto = validChocolate;
        chocolateDto.setId(null);
        ChocolateDto savedDto = ChocolateDto.builder().id(UUID.randomUUID())
                .chocolateName("New").build();
        String chocolateDtoJson = objectMapper.writeValueAsString(chocolateDto);

        given(chocolateService.saveNewChocolate(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/chocolate/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(chocolateDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handleUpdate() throws Exception {
        ChocolateDto chocolateDto = validChocolate;
        chocolateDto.setId(null);
        String chocolateDtoJson = objectMapper.writeValueAsString(chocolateDto);

        mockMvc.perform(put("/api/v1/chocolate/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(chocolateDtoJson))
                .andExpect(status().isNoContent());

        then(chocolateService).should().updateChocolate(any(), any());
    }
}
