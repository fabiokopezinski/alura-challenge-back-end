package br.com.alura.challenge.back.feature;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class CategoryTest {
    
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void create_WhenParamsIsValid_ExpectedCreate() throws Exception {
        mockMvc.perform(post("/categorias").content(asJsonString(CategoryScenarioFactory.CATEGORY_REQUEST))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void findAllCategory_ExpectedOk() throws Exception {
        mockMvc.perform(get("/categorias")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void create_WhenParamsIsInvalid_ExpectedBadQuest() throws Exception {
        mockMvc.perform(post("/categorias").content(asJsonString(CategoryScenarioFactory.CATEGORY_REQUEST_BAD))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void update_WhenParamsIsValid_ExpectedUpdated() throws Exception {
        mockMvc.perform(patch("/categorias/1").content(asJsonString(CategoryScenarioFactory.CATEGORY_UPDATE))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void update_WhenParamsIsInvalid_ExpectedException() throws Exception {
        mockMvc.perform(patch("/categorias/0").content(asJsonString(CategoryScenarioFactory.CATEGORY_UPDATE))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void findByCategoryId_WhenPathVariableIsValid_ExpectedOk() throws Exception {
        mockMvc.perform(get("/categorias/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void findByCategoryId_WhenPathVariableIsInvalid_ExpectedNotFound() throws Exception {
        mockMvc.perform(get("/categorias/0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void delete_WhenPathVariableIsInvalid_ExpectedNotFound() throws Exception {
        mockMvc.perform(delete("/categorias/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
