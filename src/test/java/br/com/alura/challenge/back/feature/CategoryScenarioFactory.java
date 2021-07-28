package br.com.alura.challenge.back.feature;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.alura.challenge.back.domain.Category;
import br.com.alura.challenge.back.domain.dto.request.CategoryRequest;
import br.com.alura.challenge.back.domain.dto.request.CategoryUpdate;
import br.com.alura.challenge.back.domain.dto.response.CategoryResponse;

public class CategoryScenarioFactory {

    public static final Page<CategoryResponse> FIND_ALL = loadFindAll();

    public static final CategoryResponse FIND_BY_ID = loadCategoryResponse();

    public static final CategoryRequest CATEGORY_REQUEST = loadCreateRequest();

    public static final CategoryUpdate CATEGORY_UPDATE = loadCreateUpdate();

    public static final Category CATEGORY = loadCategory();

    public static final Category CATEGORY_BDD = loadCategoryBDD();


    private static Page<CategoryResponse> loadFindAll() {

        PageRequest page = PageRequest.of(0, 10);

        CategoryResponse categoryResponse = new CategoryResponse(1L, "title", "color");

        List<CategoryResponse> list = new ArrayList<>();

        list.add(categoryResponse);

        return new PageImpl<CategoryResponse>(list, page, 10);
    }

    private static Category loadCategoryBDD() {

        Category category = new Category(99L, "BDD_TITLE", "BDD_COLOR");

        return category;
    }

    private static CategoryResponse loadCategoryResponse() {

        CategoryResponse categoryResponse = new CategoryResponse(1L, "title", "color");

        return categoryResponse;
    }

    private static Category loadCategory() {

        Category category = new Category(1L, "title", "color");

        return category;
    }
    
    private static CategoryRequest loadCreateRequest() {
        return CategoryRequest.builder().title("title").color("color").build();
    }
    
    private static CategoryUpdate loadCreateUpdate(){
        return CategoryUpdate.builder().title("title").color("color").build();
    }

}
