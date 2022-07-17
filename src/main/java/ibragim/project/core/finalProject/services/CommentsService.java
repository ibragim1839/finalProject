package ibragim.project.core.finalProject.services;

import ibragim.project.core.finalProject.models.Commentary;

import java.util.List;

public interface CommentsService {

    List<Commentary> getCommentsById(Long id);

    Commentary addCommentToTask(Long taskId, Commentary commentary);
}
