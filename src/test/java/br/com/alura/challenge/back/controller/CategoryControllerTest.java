package br.com.alura.challenge.back.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.challenge.back.feature.CategoryScenarioFactory;
import br.com.alura.challenge.back.service.CategoryService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void findAllCategory_ExpectedOk() throws Exception {

        when(categoryService.findAll(anyInt(), anyInt())).thenReturn(CategoryScenarioFactory.FIND_ALL);
        mockMvc.perform(get("/categorias")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void create_WhenParamsIsValid_ExpectedCreate() throws Exception {
        when(categoryService.create(any())).thenReturn(CategoryScenarioFactory.FIND_BY_ID);

        mockMvc.perform(post("/categorias").content(asJsonString(CategoryScenarioFactory.CATEGORY_REQUEST))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void update_WhenParamsIsValid_ExpectedUpdated() throws Exception {

        when(categoryService.update(any(), anyLong())).thenReturn(CategoryScenarioFactory.FIND_BY_ID);

        mockMvc.perform(patch("/categorias/1").content(asJsonString(CategoryScenarioFactory.CATEGORY_UPDATE))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void findByCategoryId_WhenPathVariableIsValid_ExpectedOk() throws Exception {

        when(categoryService.findByIdResponse(any())).thenReturn(CategoryScenarioFactory.FIND_BY_ID);

        mockMvc.perform(get("/categorias/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void delete_WhenPathVariableIsValid_ExpectedOk() throws Exception {

        doNothing().when(categoryService).delete(1L);

        mockMvc.perform(delete("/categorias/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
