package br.com.alura.challenge.back.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
