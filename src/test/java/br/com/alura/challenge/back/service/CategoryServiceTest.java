package br.com.alura.challenge.back.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.challenge.back.domain.Category;
import br.com.alura.challenge.back.domain.dto.response.CategoryResponse;
import br.com.alura.challenge.back.exception.BusinessException;
import br.com.alura.challenge.back.feature.CategoryScenarioFactory;
import br.com.alura.challenge.back.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Listar todos as categorias disponiveis")
    public void findAllCategory() {

        when(categoryRepository.findAllCategory(any(Pageable.class))).thenReturn(CategoryScenarioFactory.FIND_ALL);

        Page<CategoryResponse> findAll = categoryService.findAll(0, 1);

        assertEquals(CategoryScenarioFactory.FIND_ALL, findAll);

        verify(categoryRepository).findAllCategory(any());
    }

    @Test
    @DisplayName("Criar categorias com params válidos")
    public void create_WhenNotExistTitle_Expected() {

        when(categoryRepository.findByTitle(any())).thenReturn(Optional.empty());

        when(categoryRepository.save(any())).thenReturn(CategoryScenarioFactory.CATEGORY);

        CategoryResponse create = categoryService.create(CategoryScenarioFactory.CATEGORY_REQUEST);

        assertNotNull(create);

        verify(categoryRepository).findByTitle(any());

        verify(categoryRepository).save(any());
    }

    @Test
    @DisplayName("Criar categoria com titulo existente")
    public void create_WhenTitleExist_ExpectedBadRequest() {

        when(categoryRepository.findByTitle(any())).thenReturn(Optional.of(CategoryScenarioFactory.CATEGORY));

        assertThrows(BusinessException.class, () -> categoryService.create(CategoryScenarioFactory.CATEGORY_REQUEST));

    }

    @Test
    @DisplayName("Procurar categoria pelo id")
    public void findByIdResponse_WhenCategoryIdExist_ExpectedOk() {

        when(categoryRepository.findById(any())).thenReturn(Optional.of(CategoryScenarioFactory.CATEGORY));

        CategoryResponse findByIdResponse = categoryService.findByIdResponse(1L);

        assertNotNull(findByIdResponse);

        verify(categoryRepository).findById(any());
    }

    @Test
    @DisplayName("Procurar categoria pelo id")
    public void findById_WhenCategoryIdExist_ExpectedOk() {

        when(categoryRepository.findById(any())).thenReturn(Optional.of(CategoryScenarioFactory.CATEGORY));

        Category findById = categoryService.findById(1L);

        assertNotNull(findById);

        verify(categoryRepository).findById(any());
    }


    @Test
    @DisplayName("Procurar categoria pelo id mas não existe no banco")
    public void findById_WhenCategoryIdNotExist_ExpectedNotFound() {

        when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> categoryService.findById(2L));
    }

    @Test
    @DisplayName("Procurar categoria pelo id mas não existe no banco")
    public void findByIdResponse_WhenCategoryIdNotExist_ExpectedNotFound() {

        when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> categoryService.findByIdResponse(2L));
    }

    @Test
    @DisplayName("Atualizar category com sucesso")
    public void update_WhenCategoryIdValid_ExpectedUpdated() {

        when(categoryRepository.findById(any())).thenReturn(Optional.of(CategoryScenarioFactory.CATEGORY));

        CategoryResponse update = categoryService.update(CategoryScenarioFactory.CATEGORY_UPDATE, 1L);

        assertNotNull(update);

        verify(categoryRepository).findById(any());

    }

    @Test
    @DisplayName("Atualizar category com id inválido")
    public void update_WhenCategoryIdIsNotValid_ExpectedUpdated() {
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class,
                () -> categoryService.update(CategoryScenarioFactory.CATEGORY_UPDATE, 2L));
    }

    @Test
    @DisplayName("Deletar categoria por id")
    public void delete_WhenCategoryIdExist_ExpectedDeleted() {

        when(categoryRepository.findById(any())).thenReturn(Optional.of(CategoryScenarioFactory.CATEGORY));

        categoryService.delete(1L);
        
    }

    @Test
    @DisplayName("Deletar categoria por id,mas não existe")
    public void delete_WhenCategoryIdNotExist_ExpectedNotFound() {
     
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, ()->categoryService.delete(1L));

    }

}
