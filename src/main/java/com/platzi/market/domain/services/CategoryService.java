package com.platzi.market.domain.services;

import com.platzi.market.domain.Category;
import com.platzi.market.domain.repository.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository categoryRespository;

    public List<Category> getAll() {

        return categoryRespository.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {

        return categoryRespository.getCategory(categoryId);
    }

    public Category save(Category category) {

        return categoryRespository.save(category);
    }

    public boolean delete(int categoryId) {

        return getCategory(categoryId).map(category -> {
            categoryRespository.delete(categoryId);
            return true;
        }).orElse(false);
    }
}
