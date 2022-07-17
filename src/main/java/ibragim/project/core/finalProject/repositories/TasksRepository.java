package ibragim.project.core.finalProject.repositories;

import ibragim.project.core.finalProject.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TasksRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByFolderId(Long folder_id);

    @Modifying
    @Query("DELETE FROM Task t WHERE t.folder.id=:folder_id")
    void deleteTasksByFolderId(@Param("folder_id") Long folderId);
}
