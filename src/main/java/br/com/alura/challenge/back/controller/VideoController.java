package br.com.alura.challenge.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challenge.back.domain.dto.request.VideoRequest;
import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import br.com.alura.challenge.back.service.VideoService;
import lombok.AllArgsConstructor;

@RequestMapping("/videos")
@RestController
@AllArgsConstructor
public class VideoController {

    private VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoResponse>> findAllVideo() {
        return ResponseEntity.status(HttpStatus.OK).body(videoService.findAllVideo());
    }

    @PostMapping
    public ResponseEntity<VideoResponse> create(@RequestBody VideoRequest videoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.create(videoRequest));
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoResponse> findByVideoId(@PathVariable("videoId") Long videoId) {

        return ResponseEntity.status(HttpStatus.OK).body(videoService.findByVideoId(videoId));
    }

}
