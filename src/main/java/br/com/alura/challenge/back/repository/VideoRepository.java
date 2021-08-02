package br.com.alura.challenge.back.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.back.domain.Video;
import br.com.alura.challenge.back.domain.dto.response.VideoResponse;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("SELECT NEW br.com.alura.challenge.back.domain.dto.response.VideoResponse(v.videoId,v.title,v.categoryId,v.description,v.url) FROM Video v"+
    " INNER JOIN Category c on c.categoryId=v.categoryId WHERE (: title is null or v.title like %:title% ) ")
    Page<VideoResponse> findAllVideo(Pageable page,@Param("title") String title);

    @Query("SELECT NEW br.com.alura.challenge.back.domain.dto.response.VideoResponse(v.videoId,v.title,v.categoryId,v.description,v.url) FROM Video v"
            + " INNER JOIN Category c on c.categoryId=v.categoryId WHERE c.categoryId=:categoryId")
    Page<VideoResponse> findByCategoria(@Param("categoryId") Long categoryId,Pageable page);

    Optional<Video> findByTitle(String title);

    @Query("SELECT NEW br.com.alura.challenge.back.domain.dto.response.VideoResponse(v.videoId,v.title,v.categoryId,v.description,v.url) FROM Video v"
            + " INNER JOIN Category c on c.categoryId=v.categoryId")
    Page<VideoResponse> findAllVideoFree(Pageable page);

}
