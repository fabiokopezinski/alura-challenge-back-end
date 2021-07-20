package br.com.alura.challenge.back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import br.com.alura.challenge.back.repository.VideoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("VideoService")
@Slf4j
@AllArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public List<VideoResponse> findAllVideo() {

        log.info("method=findAllVideo");

        return videoRepository.findAllVideo();
    }
}
