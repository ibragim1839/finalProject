package ibragim.project.core.finalProject.services.Impl;

import ibragim.project.core.finalProject.models.Category;
import ibragim.project.core.finalProject.repositories.CategoriesRepository;
import ibragim.project.core.finalProject.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;
    @Override
    public List<Category> getCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id){return categoriesRepository.findById(id).orElse(null);}

    @Override
    public Category addCategory(Category category) {
        if (category!=null){
            return categoriesRepository.save(category);
        }
        return null;
    }
}
