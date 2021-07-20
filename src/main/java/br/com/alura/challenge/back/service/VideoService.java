package br.com.alura.challenge.back.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challenge.back.domain.Video;
import br.com.alura.challenge.back.domain.dto.request.VideoRequest;
import br.com.alura.challenge.back.domain.dto.request.VideoUpdate;
import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import br.com.alura.challenge.back.repository.VideoRepository;
import br.com.alura.challenge.back.validations.Message;
import br.com.alura.challenge.back.validations.OnCreate;
import br.com.alura.challenge.back.validations.OnUpdate;
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

        videoRepository.findByTitle(videoRequest.getTitle()).ifPresent(p -> {
            throw Message.VIDEO_EXIST.asBusinessException();
        });

        Video video = Video.of(videoRequest);

        videoRepository.save(video);

        log.info("method=create videoId={} title={} description={} url={}", video.getVideoId(), video.getTitle(),
                video.getDescription(), video.getUrl());

        return video.toDto();
    }

    public VideoResponse findByVideoId(Long videoId) {

        Video video = videoRepository.findById(videoId).orElseThrow(Message.NOT_FOUND_VIDEO::asBusinessException);

        log.info("method=findByVideoId videoId={}", videoId);

        return video.toDto();
    }

    @Validated(OnUpdate.class)
    @Transactional
    public VideoResponse update(Long videoId, @Valid VideoUpdate videoUpdate) {

        Video video = videoRepository.findById(videoId).orElseThrow(Message.NOT_FOUND_VIDEO::asBusinessException);

        video.update(videoUpdate);

        log.info("method=update videoId={} description={} url={}", videoId, video.getDescription(), video.getUrl());

        return video.toDto();

    }

    public void delete(Long videoId) {

        videoRepository.findById(videoId).orElseThrow(Message.NOT_FOUND_VIDEO::asBusinessException);

        videoRepository.deleteById(videoId);

        log.info("method=delete videoId={}", videoId);
    }
}
