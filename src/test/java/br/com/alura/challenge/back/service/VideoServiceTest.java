package br.com.alura.challenge.back.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import br.com.alura.challenge.back.exception.BusinessException;
import br.com.alura.challenge.back.feature.CategoryScenarioFactory;
import br.com.alura.challenge.back.feature.VideoScenarioFactory;
import br.com.alura.challenge.back.repository.VideoRepository;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private CategoryService categoryService;

    @Test
    @DisplayName("Listar por categorias")
    public void findByCategory() {

        when(videoRepository.findByCategoria(any(), any(Pageable.class))).thenReturn(VideoScenarioFactory.FIND_ALL);

        Page<VideoResponse> findByCategory = videoService.findByCategory(0, 10, 1L);

        assertNotNull(findByCategory);

        verify(videoRepository).findByCategoria(any(), any());
    }

    @DisplayName("Listar todos os videos disponiveis")
    @Test
    public void findAllVideo() {

        when(videoRepository.findAllVideo(any(Pageable.class), any())).thenReturn(VideoScenarioFactory.FIND_ALL);

        Page<VideoResponse> findAllVideo = videoService.findAllVideo(0, 10, null);

        assertNotNull(findAllVideo);

        assertEquals(VideoScenarioFactory.FIND_ALL, findAllVideo);

        verify(videoRepository).findAllVideo(any(), any());

    }

    @DisplayName("Criar video com params validos")
    @Test
    public void create_WhenNotExistTitle_ExpectedCreate() {

        when(videoRepository.save(any())).thenReturn(VideoScenarioFactory.VIDEO);

        when(videoRepository.findByTitle(any())).thenReturn(Optional.empty());

        when(categoryService.findById(anyLong())).thenReturn(CategoryScenarioFactory.CATEGORY);

        VideoResponse save = videoService.create(VideoScenarioFactory.CREATE_REQUEST);

        assertNotNull(save);

        verify(videoRepository).findByTitle(any());

        verify(videoRepository).save(any());

    }

    @DisplayName("Criar video com titulo existente")
    @Test
    public void create_WhenExistTitle_ExpectedBadRequest() {

        when(videoRepository.findByTitle(any())).thenReturn(Optional.of(VideoScenarioFactory.VIDEO));

        when(categoryService.findById(anyLong())).thenReturn(CategoryScenarioFactory.CATEGORY);

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
