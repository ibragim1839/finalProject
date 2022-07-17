package ibragim.project.core.finalProject.services.Impl;

import ibragim.project.core.finalProject.models.Task;
import ibragim.project.core.finalProject.repositories.TasksRepository;
import ibragim.project.core.finalProject.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TasksServiceImpl implements TaskService {

    @Autowired
    TasksRepository tasksRepository;


    @Override
    public List<Task> getTasksFromFolder(Long folderId) {
        List <Task> tasks = tasksRepository.findAllByFolderId(folderId);
        return tasks;
    }

    @Override
    public Task addTask(Task task) {
        if(task!=null){
            return tasksRepository.save(task);
        }
      return null;
    }

    @Override
    public Task getTaskById(Long id) {
        if(id!=null){
            Task task = tasksRepository.findById(id).orElse(null);
            return task;
        }
        return null;
    }

    @Override
    public Task changeStatusOfTheTask(Long taskId, int statusId) {
        if(taskId!=null){
            Task task = getTaskById(taskId);
            task.setStatus(statusId);
            return addTask(task);
        }
        return null;
    }

    @Override
    public void deleteTaskById(Long id) {
        tasksRepository.deleteById(id);

    }
}
