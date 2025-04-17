package tn.ucar.enicar.info.projetspring.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.ucar.enicar.info.projetspring.enums.PosteStatus;
import tn.ucar.enicar.info.projetspring.enums.PosteType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    private PosteType type;

    @Enumerated(EnumType.STRING)
    private PosteStatus status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private event event;

    @ElementCollection
    @CollectionTable(name = "poste_competences", joinColumns = @JoinColumn(name = "poste_id"))
    @Column(name = "competence")
    private List<String> competences = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "poste", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Candidature> candidatures = new HashSet<>();
}
