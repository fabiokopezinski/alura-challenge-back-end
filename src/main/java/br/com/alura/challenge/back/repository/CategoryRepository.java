package br.com.alura.challenge.back.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.challenge.back.domain.Category;
import br.com.alura.challenge.back.domain.dto.response.CategoryResponse;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    
    Optional<Category> findByTitle(String title);

    @Query("SELECT NEW br.com.alura.challenge.back.domain.dto.response.CategoryResponse(c.categoryId,c.title,c.color) FROM Category c")
    Page<CategoryResponse> findAllCategory(Pageable page);
}
