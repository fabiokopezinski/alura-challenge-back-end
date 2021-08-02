package br.com.alura.challenge.back.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challenge.back.domain.Category;
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

    private final CategoryService categoryService;

    public Page<VideoResponse> findByCategory(int page, int limit, Long categoryId) {

        Pageable pageable = PageRequest.of(page, limit);

        log.info("method=findByCategory page={} limit={} categoryId={}", page, limit, categoryId);

        return videoRepository.findByCategoria(categoryId, pageable);
    }

    public Page<VideoResponse> findAllVideo(int page, int limit,String title) {

        Pageable pageable = PageRequest.of(page, limit);

        log.info("method=findAllVideo page={} limit={} search={}", page, limit,title);

        return videoRepository.findAllVideo(pageable,title);
    }

    @Validated(OnCreate.class)
    public VideoResponse create(@Valid VideoRequest videoRequest) {

        Category category = categoryService.findById(videoRequest.getCategoryId());

        videoRepository.findByTitle(videoRequest.getTitle()).ifPresent(p -> {
            throw Message.VIDEO_EXIST.asBusinessException();
        });

        Video video = Video.of(videoRequest);

        video.addCategory(category);

        videoRepository.save(video);

        log.info("method=create videoId={} title={} description={} url={} categoryId={}", video.getVideoId(),
                video.getTitle(), video.getDescription(), video.getUrl(), video.getCategoryId());

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

    public Page<VideoResponse> free() {

        int limit = 10;
        int page = 0;

        Pageable pageable = PageRequest.of(page, limit);

        log.info("method=free limit={}",limit);

        return videoRepository.findAllVideoFree(pageable);
    }
}
