package ibragim.project.core.finalProject.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="t_tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title")
    private String title;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "status")
    private int status;

    @ManyToOne
    private Folder folder;
}
