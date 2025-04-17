package tn.ucar.enicar.info.projetspring.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.ucar.enicar.info.projetspring.enums.StatusCandidat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "poste_id"}
))
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User candidat;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poste_id")
    private Poste poste;

    private String experience;
    private String coverLetter;
    private String cvPath;

    @Enumerated(EnumType.STRING)
    private StatusCandidat status;
}
