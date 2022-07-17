package ibragim.project.core.finalProject.services;
import ibragim.project.core.finalProject.models.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {

    public List<Task> getTasksFromFolder(Long folderId);

    public Task addTask(Task task);

    public Task getTaskById(Long id);

    public Task changeStatusOfTheTask(Long taskId, int statusId);

    public void deleteTaskById(Long id);
}
