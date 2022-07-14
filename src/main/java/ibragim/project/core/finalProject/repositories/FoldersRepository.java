package ibragim.project.core.finalProject.repositories;

import ibragim.project.core.finalProject.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FoldersRepository extends JpaRepository<Folder, Long> {
}
