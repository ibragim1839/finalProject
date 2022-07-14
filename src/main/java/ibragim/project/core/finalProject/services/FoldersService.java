package ibragim.project.core.finalProject.services;

import ibragim.project.core.finalProject.models.Category;
import ibragim.project.core.finalProject.models.Folder;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FoldersService {
    public List<Folder> getFolders();
    public Folder getFolder(Long id);
    public Folder addNewCategoryToFolder(Category category, Long folderId);
    public Folder addCategoryToFolder(Long categoryId, Long folderId);
    public Folder addNewFolder(Folder folder);
}
