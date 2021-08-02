package br.com.alura.challenge.back.feature;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class VideoTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void findAllVideo() throws Exception {
        mockMvc.perform(get("/videos")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());
    }

    @Test
    public void findByCategory() throws Exception {
        mockMvc.perform(get("/videos/1/categorias")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());
    }

    @Test
    public void create_WhenParamsIsValid_ExpectedCreate() throws Exception {
        mockMvc.perform(post("/videos").content(asJsonString(VideoScenarioFactory.CREATE_REQUEST))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void create_WhenParamsIsInvalid_ExpectedBadRequest() throws Exception {
        mockMvc.perform(post("/videos").content(asJsonString(VideoScenarioFactory.CREATE_REQUEST_BAD_REQUEST))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    public void findByVideoId_WhenPathVariableIsValid_ExpectedOk() throws Exception {
        mockMvc.perform(get("/videos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findByVideoId_WhenPathVariableIsInvalid_ExpectedOk() throws Exception {
        mockMvc.perform(get("/videos/0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void update_WhenParamsIsValid_ExpectedUpdated() throws Exception {
        mockMvc.perform(patch("/videos/1").content(asJsonString(VideoScenarioFactory.VIDEO_UPDATE))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void update_WhenParamsIsInvalid_ExpectedUpdated() throws Exception {
        mockMvc.perform(patch("/videos/0").content(asJsonString(VideoScenarioFactory.VIDEO_UPDATE))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    @Test
    public void delete_WhenPathVariableIsValid_ExpectedOk() throws Exception {
        mockMvc.perform(delete("/videos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    @Test
    public void delete_WhenPathVariableIsInvalid_ExpectedOk() throws Exception {
        mockMvc.perform(delete("/videos/0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
