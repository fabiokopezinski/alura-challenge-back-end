package br.com.alura.challenge.back.feature;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.challenge.back.domain.dto.response.VideoResponse;

public class VideoScenarioFactory {

    public static final List<VideoResponse> FIND_ALL = loadFindAll();

    private static List<VideoResponse> loadFindAll() {

        VideoResponse video = new VideoResponse(1L, "title", "description", "url");

        List<VideoResponse> list = new ArrayList<>();

        list.add(video);

        return list;
    }
}
