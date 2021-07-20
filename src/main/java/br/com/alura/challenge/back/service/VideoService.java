package br.com.alura.challenge.back.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challenge.back.domain.Video;
import br.com.alura.challenge.back.domain.dto.request.VideoRequest;
import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import br.com.alura.challenge.back.repository.VideoRepository;
import br.com.alura.challenge.back.validations.OnCreate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("VideoService")
@Slf4j
@Validated
@AllArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public List<VideoResponse> findAllVideo() {

        log.info("method=findAllVideo");

        return videoRepository.findAllVideo();
    }

    @Validated(OnCreate.class)
    public VideoResponse create(@Valid VideoRequest videoRequest) {

        Video video = Video.of(videoRequest);

        videoRepository.save(video);

        log.info("method=create videoId={} title={} description={} url={}", video.getVideoId(), video.getTitle(),
                video.getDescription(), video.getUrl());

        return video.toDto();
    }
}
