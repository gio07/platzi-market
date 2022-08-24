package com.platzi.market.domain.repository;

import com.platzi.market.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRespository {

    List<Category> getAll();

    Optional<Category> getCategory(int categoryId);

    Category save(Category category);

    void delete(int categoryId);
}
