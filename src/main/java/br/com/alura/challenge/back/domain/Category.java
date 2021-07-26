package br.com.alura.challenge.back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.alura.challenge.back.domain.dto.request.CategoryRequest;
import br.com.alura.challenge.back.domain.dto.request.CategoryUpdate;
import br.com.alura.challenge.back.domain.dto.response.CategoryResponse;
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
@Table(name = "TB_CATEGORIA")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id", nullable = false)
    private Long categoryId;

    @Column(name = "title", nullable = false, columnDefinition = "varchar(255)")
    private String title;

    @Column(name = "color", nullable = false, columnDefinition = "varchar(255)")
    private String color;
    

    public static final Category of(CategoryRequest categoryRequest) {
        return Category.builder().title(categoryRequest.getTitle()).color(categoryRequest.getColor()).build();
    }

    public void update(CategoryUpdate categoryUpdate) {

        if (categoryUpdate.getTitle() != null) {
            this.title = categoryUpdate.getTitle();
        }
        if (categoryUpdate.getColor() != null) {
            this.color = categoryUpdate.getColor();
        }
    }

    public CategoryResponse toDTO() {
        return CategoryResponse.builder().categoryId(this.categoryId).title(this.title).color(this.color).build();
    }
}
