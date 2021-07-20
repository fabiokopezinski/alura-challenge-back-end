package br.com.alura.challenge.back.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VideoResponse {

    private Long videoId;

    private String title;

    private String description;

    private String url;
}
