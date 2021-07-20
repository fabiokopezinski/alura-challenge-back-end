package br.com.alura.challenge.back.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import br.com.alura.challenge.back.exception.BusinessException;
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

    @DisplayName("Criar video com params validos")
    @Test
    public void create_WhenNotExistTitle_ExpectedCreate() {

        when(videoRepository.save(any())).thenReturn(VideoScenarioFactory.VIDEO);

        when(videoRepository.findByTitle(any())).thenReturn(Optional.empty());

        VideoResponse save = videoService.create(VideoScenarioFactory.CREATE_REQUEST);

        assertNotNull(save);

        verify(videoRepository).findByTitle(any());

        verify(videoRepository).save(any());

    }

    @DisplayName("Criar video com titulo existente")
    @Test
    public void create_WhenExistTitle_ExpectedBadRequest() {

        when(videoRepository.findByTitle(any())).thenReturn(Optional.of(VideoScenarioFactory.VIDEO));

        assertThrows(BusinessException.class, () -> videoService.create(VideoScenarioFactory.CREATE_REQUEST));
    }

    @Test
    @DisplayName("Procurar video pelo id")
    public void findByVideoId_WhenVideoIdExist_ExpectedOk() {

        when(videoRepository.findById(any())).thenReturn(Optional.of(VideoScenarioFactory.VIDEO));

        VideoResponse findByVideoId = videoService.findByVideoId(1L);

        assertNotNull(findByVideoId);

        verify(videoRepository).findById(any());
    }

    @Test
    @DisplayName("Procurar video pelo id, mas não existe no banco")
    public void findByVideoId_WhenVideoIdNotExist_ExpectedFail() {

        when(videoRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> videoService.findByVideoId(1L));

    }

    @DisplayName("Deletar por video id válido")
    @Test
    public void delete_WhenVideoIdExist_ExpectedDeleted() {

        when(videoRepository.findById(any())).thenReturn(Optional.of(VideoScenarioFactory.VIDEO));

        videoService.delete(1L);

    }

    @DisplayName("Deletar por video id mas não existe no banco")
    @Test
    public void delete_WhenVideoIdNotExist_ExpectedNotFound() {

        when(videoRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> videoService.delete(1L));
    }

    @DisplayName("Atualizar video com sucesso")
    @Test
    public void update_WhenVideoIdIsValid_ExpectedUpdated() {

        when(videoRepository.findById(any())).thenReturn(Optional.of(VideoScenarioFactory.VIDEO));

        VideoResponse update = videoService.update(1L, VideoScenarioFactory.VIDEO_UPDATE);

        assertNotNull(update);

        verify(videoRepository).findById(any());

    }

    @DisplayName("Atualizar video com id inválido")
    @Test
    public void update_WhenVideoIdIsInvalid_ExpectedNotFound() {

        when(videoRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> videoService.update(1L, VideoScenarioFactory.VIDEO_UPDATE));

    }
}
