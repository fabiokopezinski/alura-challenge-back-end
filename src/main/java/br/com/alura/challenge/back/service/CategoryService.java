package br.com.alura.challenge.back.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.alura.challenge.back.domain.Category;
import br.com.alura.challenge.back.domain.dto.request.CategoryRequest;
import br.com.alura.challenge.back.domain.dto.request.CategoryUpdate;
import br.com.alura.challenge.back.domain.dto.response.CategoryResponse;
import br.com.alura.challenge.back.repository.CategoryRepository;
import br.com.alura.challenge.back.validations.Message;
import br.com.alura.challenge.back.validations.OnCreate;
import br.com.alura.challenge.back.validations.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service("CategoryService")
@Validated
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<CategoryResponse> findAll(int page, int limit) {

        PageRequest pagePageable = PageRequest.of(page, limit);

        log.info("method=findAll page={} limit={}", page, limit);

        Page<CategoryResponse> findAll = categoryRepository.findAllCategory(pagePageable);

        return findAll;
    }

    public void delete(Long categoryId) {

        log.info("method=delete categoryId={}", categoryId);

        findById(categoryId);

        categoryRepository.deleteById(categoryId);
    }

    @Validated(OnUpdate.class)
    @Transactional
    public CategoryResponse update(@Valid CategoryUpdate categoryUpdate, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> Message.NOT_FOUND_CATEGORY.asBusinessException());

        category.update(categoryUpdate);

        log.info("method=update categoryId={} title={} color={}", categoryId, category.getTitle(), category.getColor());

        return category.toDTO();

    }

    public CategoryResponse findByIdResponse(Long categoryId) {

        log.info("method=findById categoryId={}", categoryId);

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> Message.NOT_FOUND_CATEGORY.asBusinessException()).toDTO();
    }

    public Category findById(Long categoryId) {

        log.info("method=findById categoryId={}", categoryId);

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> Message.NOT_FOUND_CATEGORY.asBusinessException());
    }

    @Validated(OnCreate.class)
    public CategoryResponse create(@Valid CategoryRequest categoryRequest) {

        categoryRepository.findByTitle(categoryRequest.getTitle()).ifPresent(p -> {
            throw Message.CATEGORY.asBusinessException();
        });

        Category category = Category.of(categoryRequest);

        categoryRepository.save(category);

        return category.toDTO();
    }
}
