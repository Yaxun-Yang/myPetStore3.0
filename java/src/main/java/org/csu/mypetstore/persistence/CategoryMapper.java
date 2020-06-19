package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    void insertCategory(Category category);

    void updateCategory(Category category);

    List<Category> getCategoryList();

    Category getCategoryByCategoryId(String categoryId);

    void deleteCategoryList();

    void deleteCategory(String categoryId);
}
