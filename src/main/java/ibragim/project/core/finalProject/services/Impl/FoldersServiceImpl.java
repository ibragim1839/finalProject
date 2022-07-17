package ibragim.project.core.finalProject.services.Impl;

import ibragim.project.core.finalProject.models.Category;
import ibragim.project.core.finalProject.models.Folder;
import ibragim.project.core.finalProject.repositories.CategoriesRepository;
import ibragim.project.core.finalProject.repositories.FoldersRepository;
import ibragim.project.core.finalProject.repositories.TasksRepository;
import ibragim.project.core.finalProject.services.CategoriesService;
import ibragim.project.core.finalProject.services.FoldersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoldersServiceImpl implements FoldersService {

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    CategoriesRepository categoriesRepository;


    @Autowired
    FoldersRepository foldersRepository;

    @Autowired
    TasksRepository tasksRepository;


    @Override
    public List<Folder> getFolders() {
        return foldersRepository.findAll();
    }

    @Override
    public Folder getFolder(Long id) {
        return foldersRepository.findById(id).orElse(null);
    }

    @Override
    public Folder addNewCategoryToFolder(Category category, Long folderId) {
        Folder folder = foldersRepository.findById(folderId).orElse(null);
        if(folder!=null){
            List<Category> categories = folder.getCategories();
            if(categories==null){
                categories = new ArrayList<>();
            }
            if(category!=null){
                categoriesService.addCategory(category);
                categories.add(category);
                folder.setCategories(categories);
                return foldersRepository.save(folder);
            }
        }
        return null;
    }

    @Override
    public Folder addCategoryToFolder(Long categoryId, Long folderId) {
        Folder folder = foldersRepository.findById(folderId).orElse(null);
        Category category = categoriesService.getCategoryById(categoryId);
        if(folder!=null && category!=null){
            List<Category> categories = folder.getCategories();
            if(categories==null){
                categories = new ArrayList<>();
            }
            categories.add(category);

            folder.setCategories(categories);
            return foldersRepository.save(folder);
        }
        return null;
    }

    @Override
    public Folder addNewFolder(Folder folder) {
        if (folder!=null){ return foldersRepository.save(folder);}
        return null;
    }

    @Override
    public void deleteFolderById(Long id) {
        if (id!=null){
            tasksRepository.deleteTasksByFolderId(id);
            foldersRepository.deleteById(id);
        }
    }

    @Override
    public Folder deleteCategoryFromFolder(Long folderId, Long categoryId) {
        if(folderId!=null && categoryId!=null){
            Folder folder = foldersRepository.findById(folderId).orElse(null);
            assert folder != null;
            List<Category> categories = folder.getCategories();
            if(categories!=null){
                Category category = categoriesService.getCategoryById(categoryId);
                categories.remove(category);
                folder.setCategories(categories);

                int number = categoriesRepository.checkIsCategoryExist(categoryId);
                if(number==0){
                    categoriesService.deleteById(categoryId);
                }
                return addNewFolder(folder);
            }
        }
        return null;
    }
}
