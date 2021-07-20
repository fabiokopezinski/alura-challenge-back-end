package br.com.alura.challenge.back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoResponse {

    private Long videoId;

    private String title;

    private String description;

    private String url;
}
