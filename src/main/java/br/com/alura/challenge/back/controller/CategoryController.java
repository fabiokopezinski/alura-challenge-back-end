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
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import br.com.alura.challenge.back.annotations.CategoryCreateCodeStandard;
import br.com.alura.challenge.back.annotations.CategoryDeleteCodeStandard;
import br.com.alura.challenge.back.annotations.CategoryGetIdCodeStandard;
import br.com.alura.challenge.back.annotations.CategoryListAllCodeStandard;
import br.com.alura.challenge.back.annotations.CategoryPatchCodeStandard;
import br.com.alura.challenge.back.domain.dto.request.CategoryRequest;
import br.com.alura.challenge.back.domain.dto.request.CategoryUpdate;
import br.com.alura.challenge.back.domain.dto.response.CategoryResponse;
import br.com.alura.challenge.back.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RequestMapping("/categorias")
@RestController
@AllArgsConstructor
@Tag(name = "Categoria")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @CategoryListAllCodeStandard
    public ResponseEntity<Page<CategoryResponse>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        return ResponseEntity.ok().body(categoryService.findAll(page, limit));
    }

    @PostMapping
    @CategoryCreateCodeStandard
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryRequest));
    }

    @PatchMapping("/{categoryId}")
    @CategoryPatchCodeStandard
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryUpdate categoryUpdate,
            @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(categoryUpdate, categoryId));
    }

    @GetMapping("/{categoryId}")
    @CategoryGetIdCodeStandard
    public ResponseEntity<CategoryResponse> findById(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok().body(categoryService.findByIdResponse(categoryId));
    }

    @DeleteMapping("/{categoryId}")
    @CategoryDeleteCodeStandard
    public ResponseEntity<Void> delete(@PathVariable("categoryId") Long categoryId) {

        categoryService.delete(categoryId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
