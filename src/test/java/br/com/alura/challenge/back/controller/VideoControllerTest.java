package br.com.alura.challenge.back.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.challenge.back.feature.VideoScenarioFactory;
import br.com.alura.challenge.back.service.VideoService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    @Test
    public void findAllVideo_ExpectedOk() throws Exception {
        when(videoService.findAllVideo(anyInt(),anyInt(),any())).thenReturn(VideoScenarioFactory.FIND_ALL);
        mockMvc.perform(get("/videos")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void findByCategory_ExpectedOk() throws Exception {
        when(videoService.findByCategory(anyInt(), anyInt(), any())).thenReturn(VideoScenarioFactory.FIND_ALL);
        mockMvc.perform(get("/videos/1/categorias")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void create_WhenParamsIsValid_ExpectedCreate() throws Exception {

        when(videoService.create(any())).thenReturn(VideoScenarioFactory.VIDEO_RESPONSE);
        mockMvc.perform(post("/videos").content(asJsonString(VideoScenarioFactory.CREATE_REQUEST))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void update_WhenParamsIsValid_ExpectedUpdated() throws Exception {
        when(videoService.update(anyLong(), any())).thenReturn(VideoScenarioFactory.VIDEO_RESPONSE);

        mockMvc.perform(patch("/videos/1").content(asJsonString(VideoScenarioFactory.VIDEO_UPDATE))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void findByVideoId_WhenPathVariableIsValid_ExpectedOk() throws Exception {

        when(videoService.findByVideoId(any())).thenReturn(VideoScenarioFactory.VIDEO_RESPONSE);

        mockMvc.perform(get("/videos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void delete_WhenPathVariableIsValid_ExpectedOk() throws Exception {

        doNothing().when(videoService).delete(1L);

        mockMvc.perform(delete("/videos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
