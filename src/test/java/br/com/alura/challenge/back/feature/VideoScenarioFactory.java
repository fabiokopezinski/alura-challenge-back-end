package br.com.alura.challenge.back.feature;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.alura.challenge.back.domain.Video;
import br.com.alura.challenge.back.domain.dto.request.VideoRequest;
import br.com.alura.challenge.back.domain.dto.request.VideoUpdate;
import br.com.alura.challenge.back.domain.dto.response.VideoResponse;

public class VideoScenarioFactory {

    public static final Page<VideoResponse> FIND_ALL = loadFindAll();

    public static final VideoRequest CREATE_REQUEST = loadCreateRequest();

    public static final Video VIDEO = loadVideo();

    public static final VideoResponse VIDEO_RESPONSE = loadVideoResponse();

    public static final VideoUpdate VIDEO_UPDATE = loadlVideoUpdate();

    private static Page<VideoResponse> loadFindAll() {

        PageRequest page = PageRequest.of(0, 10);

        VideoResponse video = new VideoResponse(1L, "title", "description", "http://teste.com");

        List<VideoResponse> list = new ArrayList<>();

        list.add(video);
        
        return new PageImpl<VideoResponse>(list, page, 10);
    }

    private static VideoUpdate loadlVideoUpdate() {

        VideoUpdate video = new VideoUpdate("description", "http://teste.com");

        return video;
    }

    private static VideoResponse loadVideoResponse() {

        VideoResponse video = new VideoResponse(1L, "title", "description", "http://teste.com");

        return video;
    }

    private static Video loadVideo() {

        Video video = new Video(1L, "title", "description", "http://teste.com",null);
        return video;
    }

    private static VideoRequest loadCreateRequest() {
        return VideoRequest.builder().title("title").description("description").url("http://teste.com").build();
    }
}
