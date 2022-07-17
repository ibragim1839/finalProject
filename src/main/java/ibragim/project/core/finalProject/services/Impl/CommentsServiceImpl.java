package ibragim.project.core.finalProject.services.Impl;

import ibragim.project.core.finalProject.models.Commentary;
import ibragim.project.core.finalProject.models.Task;
import ibragim.project.core.finalProject.repositories.CommentsRepository;
import ibragim.project.core.finalProject.repositories.TasksRepository;
import ibragim.project.core.finalProject.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    TasksRepository tasksRepository;


    @Override
    public List<Commentary> getCommentsById(Long id) {
        return commentsRepository.getAllByTaskId(id);
    }

    @Override
    public Commentary addCommentToTask(Long taskId, Commentary commentary) {
        Task t =tasksRepository.findById(taskId).orElse(null);
        if(t!=null){
            commentary.setTask(t);
            return commentsRepository.save(commentary);
        }
        return null;
    }
}
