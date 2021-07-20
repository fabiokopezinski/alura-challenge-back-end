package br.com.alura.challenge.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.back.domain.Video;
import br.com.alura.challenge.back.domain.dto.response.VideoResponse;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("SELECT NEW br.com.alura.challenge.back.domain.dto.response.VideoResponse(v.videoId,v.title,v.description,v.url) FROM Video v")
    List<VideoResponse> findAllVideo();

    Video findByTitle(String title);

}
