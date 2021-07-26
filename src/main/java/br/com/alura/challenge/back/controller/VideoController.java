package br.com.alura.challenge.back.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challenge.back.annotations.VideoCreateCodeStandard;
import br.com.alura.challenge.back.annotations.VideoDeleteCodeStandard;
import br.com.alura.challenge.back.annotations.VideoGetIdCodeStandard;
import br.com.alura.challenge.back.annotations.VideoListAllCodeStandard;
import br.com.alura.challenge.back.annotations.VideoPatchCodeStandard;
import br.com.alura.challenge.back.domain.dto.request.VideoRequest;
import br.com.alura.challenge.back.domain.dto.request.VideoUpdate;
import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import br.com.alura.challenge.back.service.VideoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RequestMapping("/videos")
@RestController
@AllArgsConstructor
@Tag(name = "Videos")
public class VideoController {

    private VideoService videoService;

    @GetMapping
    @VideoListAllCodeStandard
    public ResponseEntity<Page<VideoResponse>> findAllVideo(@RequestParam(required = false,defaultValue = "0",name = "page")int page,@RequestParam(required = false,defaultValue = "10",name="limit" )int limit ){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.findAllVideo(page,limit));
    }

    @PostMapping
    @VideoCreateCodeStandard
    public ResponseEntity<VideoResponse> create(@RequestBody VideoRequest videoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.create(videoRequest));
    }

    @GetMapping("/{videoId}")
    @VideoGetIdCodeStandard
    public ResponseEntity<VideoResponse> findByVideoId(@PathVariable("videoId") Long videoId) {
        return ResponseEntity.status(HttpStatus.OK).body(videoService.findByVideoId(videoId));
    }

    @PatchMapping("/{videoId}")
    @VideoPatchCodeStandard
    public ResponseEntity<VideoResponse> update(@PathVariable("videoId") Long videoId,
            @RequestBody VideoUpdate videoUpdate) {
        return ResponseEntity.status(HttpStatus.OK).body(videoService.update(videoId, videoUpdate));
    }

    @DeleteMapping("/{videoId}")
    @VideoDeleteCodeStandard
    public ResponseEntity<Void> delete(@PathVariable("videoId") Long videoId) {
        videoService.delete(videoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
