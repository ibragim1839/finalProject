package ibragim.project.core.finalProject.services;

import ibragim.project.core.finalProject.models.Category;

import java.util.List;

public interface CategoriesService {
    List<Category> getCategories();
    Category getCategoryById(Long id);
    Category addCategory(Category category);
}
