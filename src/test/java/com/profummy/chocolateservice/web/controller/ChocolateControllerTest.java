package com.profummy.chocolateservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.profummy.chocolateservice.services.ChocolateService;
import com.profummy.chocolateservice.web.model.ChocolateDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
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
        validChocolate = ChocolateDto.builder()
                .chocolateName("Galaxy")
                .chocolateType("Dark")
                .barcode(123456789L)
                .price(new BigDecimal("5.50"))
                .build();
    }

    @Test
    void getChocolate() throws Exception {

        given(chocolateService.getChocolateById(any(UUID.class))).willReturn(validChocolate);

        mockMvc.perform(get("/api/v1/chocolate/{chocolateId}", validChocolate.getId().toString())
                .param("isTasty", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validChocolate.getId().toString())))
                .andExpect(jsonPath("$.chocolateName", is("Galaxy")))
                .andDo(document("v1/chocolate",
                        pathParameters(parameterWithName("chocolateId").description("UUID of the chocolate to get")
                        ),
                        requestParameters(parameterWithName("isTasty").description("isTasty")
                        ),
                        responseFields( // use all fields from DTO e.g. id, name or test will fail
                                fieldWithPath("id").description("Id of chocolate")
                        )));


    }

    @Test
    void saveNewChocolate() throws Exception {
        ChocolateDto chocolateDto = validChocolate;
        ChocolateDto savedDto = ChocolateDto.builder().id(UUID.randomUUID())
                .chocolateName("New").build();
        String chocolateDtoJson = objectMapper.writeValueAsString(chocolateDto);

        given(chocolateService.saveNewChocolate(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/chocolate/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(chocolateDtoJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/chocolate",
                        requestFields(fieldWithPath("id").ignored(),
                                fieldWithPath("version").ignored(),
                                fieldWithPath("createdDate").ignored(),
                                fieldWithPath("lastModifiedDate").ignored(),
                                fieldWithPath("chocolateName").description("Name of chocolate"),
                                fieldWithPath("chocolateType").description("Type of chocolate"),
                                fieldWithPath("barcode").description("Barcode of chocolate"),
                                fieldWithPath("price").description("Price of chocolate"),
                                fieldWithPath("quantityOnHand").ignored())));
    }

    @Test
    void updateChocolateById() throws Exception {
        ChocolateDto chocolateDto = validChocolate;
        String chocolateDtoJson = objectMapper.writeValueAsString(chocolateDto);

        mockMvc.perform(put("/api/v1/chocolate/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(chocolateDtoJson))
                .andExpect(status().isNoContent());

        then(chocolateService).should().updateChocolate(any(), any());
    }
}
