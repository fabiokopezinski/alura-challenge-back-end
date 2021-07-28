package br.com.alura.challenge.back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.alura.challenge.back.domain.dto.request.VideoRequest;
import br.com.alura.challenge.back.domain.dto.request.VideoUpdate;
import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "TB_VIDEO")
@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id", nullable = false)
    private Long videoId;

    @Column(name = "title", nullable = false, columnDefinition = "varchar(255)")
    private String title;

    @Column(name = "descricao", nullable = false, columnDefinition = "varchar(255)")
    private String description;

    @Column(name = "uri", nullable = false, columnDefinition = "varchar(255)")
    private String url;

    @Column(name="categoria_id",nullable=true,insertable = false,updatable = false)
    private Long categoryId;

    @OneToOne(optional = false)
    @JoinColumn(name = "categoria_id",referencedColumnName = "categoria_id")
    private Category category;

    public static Video of(VideoRequest videoRequest) {        
        return Video.builder().title(videoRequest.getTitle()).description(videoRequest.getDescription())
                .url(videoRequest.getUrl()).build();
    }

    public VideoResponse toDto() {
        return VideoResponse.builder().videoId(this.videoId).title(this.title).categoryId(this.category.getCategoryId()).description(this.description)
                .url(this.url).build();
    }


    public void addCategory(Category category) {
        this.category = category;
        this.categoryId = category.getCategoryId();
    }

    public void update(VideoUpdate videoUpdate) {

        if (videoUpdate.getDescription() != null && videoUpdate.getDescription() != "") {
            this.description = videoUpdate.getDescription();
        }

        if (videoUpdate.getUrl() != null && videoUpdate.getUrl() != "") {
            this.url = videoUpdate.getUrl();
        }
    }
}
