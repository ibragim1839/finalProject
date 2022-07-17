package ibragim.project.core.finalProject.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "text" ,columnDefinition = "TEXT")
    String text;

    @ManyToOne
    Task task;
}
