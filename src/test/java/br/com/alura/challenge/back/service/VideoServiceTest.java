package br.com.alura.challenge.back.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import br.com.alura.challenge.back.feature.VideoScenarioFactory;
import br.com.alura.challenge.back.repository.VideoRepository;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoRepository videoRepository;

    @DisplayName("Listar todos os videos disponiveis")
    @Test
    public void findAllVideo() {

        when(videoRepository.findAllVideo()).thenReturn(VideoScenarioFactory.FIND_ALL);

        List<VideoResponse> findAllVideo = videoService.findAllVideo();

        assertNotNull(findAllVideo);

        assertEquals(VideoScenarioFactory.FIND_ALL.size(), findAllVideo.size());

        verify(videoRepository).findAllVideo();

    }

}
