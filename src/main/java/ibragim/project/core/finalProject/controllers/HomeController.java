package ibragim.project.core.finalProject.controllers;

import ibragim.project.core.finalProject.models.Category;
import ibragim.project.core.finalProject.models.Commentary;
import ibragim.project.core.finalProject.models.Folder;
import ibragim.project.core.finalProject.models.Task;
import ibragim.project.core.finalProject.services.CategoriesService;
import ibragim.project.core.finalProject.services.CommentsService;
import ibragim.project.core.finalProject.services.FoldersService;
import ibragim.project.core.finalProject.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    FoldersService foldersService;

    @Autowired
    TaskService taskService;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    CommentsService commentsService;

    @GetMapping(value = "/")
    public String getMainPage(Model model){
        List<Folder> folders = foldersService.getFolders();
        model.addAttribute("folders", folders);
        return "homePage";
    }

    @GetMapping(value = "/folder/{folderId}")
    public String getFolder(Model model,
                            @PathVariable(name="folderId") Long folderId){
        List<Task> tasks = taskService.getTasksFromFolder(folderId);
        List<Category> categories = foldersService.getFolder(folderId).getCategories();
        List<Category> allCategories = categoriesService.getCategories();
        Folder folder = foldersService.getFolder(folderId);
        model.addAttribute("folder", folder);
        if(tasks!=null){
            model.addAttribute("tasks", tasks);
        }
        if (categories!=null){
            model.addAttribute("categories", categories);
        }
        if (allCategories!=null){
            if(categories!=null){
                allCategories.removeAll(categories);
            }
            model.addAttribute("allCategories", allCategories);
        }
        return "folderPage";
    }

    @PostMapping(value = "/addTask")
    public String addTask(@RequestParam(name="title") String title,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "folderId") Long folderId){
        Task theTask = new Task();
        Folder theFolder = foldersService.getFolder(folderId);
        theTask.setTitle(title);
        theTask.setDescription(description);
        theTask.setFolder(theFolder);
        theTask.setStatus(1);
        taskService.addTask(theTask);
        return("redirect:/folder/"+folderId);
    }

    @PostMapping(value = "/addNewFolder")
    public String addNewFolder(@RequestParam(name = "title") String title,
                               @RequestParam(name = "description") String description){
        Folder folder = new Folder();
        folder.setName(title);
        folder.setDescription(description);
        foldersService.addNewFolder(folder);
        return "redirect:/";
    }

    @PostMapping(value = "/addCategoryToFolder")
    public String addCategoryToFolder(@RequestParam(name = "folderId") Long folderId,
                                      @RequestParam(name = "category") Long categoryId){
        Folder folder = foldersService.addCategoryToFolder(categoryId, folderId);
        if (folder!=null){
            foldersService.addNewFolder(folder);
            return "redirect:/folder/"+folder.getId();
        }
        return null;
    }

    @PostMapping(value = "/addNewCategoryToFolder")
    public String addNewCategoryToFolder(@RequestParam(name = "folderId") Long folderId,
                                      @RequestParam(name = "category") String category){
        Category newCategory = new Category();
        newCategory.setName(category);
        Folder folder = foldersService.addNewCategoryToFolder(newCategory, folderId);
        if (folder!=null){
            foldersService.addNewFolder(folder);
            return "redirect:/folder/"+folder.getId();
        }
        return null;
    }

    @PostMapping(value = "/deleteFolder")
    public String deleteFolder(@RequestParam("folderId") Long folderId){
        if(folderId!=null){
            foldersService.deleteFolderById(folderId);
        }
        return "redirect:/";
    }

    @PostMapping(value = "/deleteCategory")
    public String deleteCategory(@RequestParam("folder_id") Long folderId,
                                 @RequestParam("category_id") Long categoryId){
        Folder folder = foldersService.deleteCategoryFromFolder(folderId, categoryId);
        return "redirect:/folder/"+folder.getId();
    }

    @GetMapping(value = "/task/{taskId}")
    public String getTaskDetails(@PathVariable("taskId") Long id,
                                 Model model){
        Task task = taskService.getTaskById(id);
        List<Commentary> commentaries = commentsService.getCommentsById(id);
        if (!commentaries.isEmpty()){
            model.addAttribute("comments", commentaries);
        }
        model.addAttribute("task", task);
        return"taskPage";
    }

    @PostMapping("/renewTaskInfo")
    public String renewTaskInfo(@RequestParam(name = "id")Long id,
                                @RequestParam(name = "status") int status){
        Task task = taskService.changeStatusOfTheTask(id, status);
        return "redirect:/folder/"+task.getFolder().getId();
    }

    @PostMapping(value = "/deleteTask")
    public String deleteTask(@RequestParam("taskId") Long taskId){
        Task t = taskService.getTaskById(taskId);
        taskService.deleteTaskById(taskId);
        return "redirect:/folder/"+t.getFolder().getId();
    }

    @PostMapping(value = "/addComment")
    public String addComment(@RequestParam("text") String text,
                             @RequestParam("taskId") Long taskId){
        if(taskId!=null && text!=null){
            Commentary commentary = new Commentary();
            commentary.setText(text);
            commentsService.addCommentToTask(taskId, commentary);
            return "redirect:/task/"+taskId;
        }
        return null;
    }
}
