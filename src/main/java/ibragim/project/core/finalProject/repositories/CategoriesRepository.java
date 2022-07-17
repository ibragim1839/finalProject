package ibragim.project.core.finalProject.repositories;

import ibragim.project.core.finalProject.models.Category;
import ibragim.project.core.finalProject.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoriesRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT COUNT(categories_id) FROM t_folders_categories WHERE categories_id=?", nativeQuery = true)
    int checkIsCategoryExist(@Param("?") Long id);
}
