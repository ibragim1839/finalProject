package ibragim.project.core.finalProject.repositories;

import ibragim.project.core.finalProject.models.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Commentary, Long> {

    List<Commentary> getAllByTaskId(Long id);
}
