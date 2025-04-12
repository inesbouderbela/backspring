package tn.ucar.enicar.info.projetspring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Poste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String type;
    private String status;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    private List<String> competences;


    @ManyToOne
    @JoinColumn(name = "event_id")
    private event event;
}
