package tn.ucar.enicar.info.projetspring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;
import tn.ucar.enicar.info.projetspring.enums.TaskStatus;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class task implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title ;
    private String description ;
    private Date deadline ;
    @Enumerated(EnumType.STRING)
    private TaskStatus Status ;
    private int note ;

    @ManyToOne
    User user;

    @ManyToOne
    event event ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="task")
    private Set<comment> comments;
    @ManyToOne
    @JoinColumn(name = "assigned_to_id") // matches the mappedBy reference
    @JsonBackReference
    private User assignedTo; // This must match the mappedBy value
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
