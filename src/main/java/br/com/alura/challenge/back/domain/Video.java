package br.com.alura.challenge.back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@Table(name = "TB_VIDEO")
@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videoId", nullable = false)
    private Long videoId;

    @Column(name = "title", nullable = false, columnDefinition = "varchar(255)")
    private String title;

    @Column(name = "descricao", nullable = false, columnDefinition = "varchar(255)")
    private String description;

    @Column(name = "uri", nullable = false, columnDefinition = "varchar(255)")
    private String url;
}
