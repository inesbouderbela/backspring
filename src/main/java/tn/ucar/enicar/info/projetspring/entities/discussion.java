package tn.ucar.enicar.info.projetspring.entities;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class discussion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="discussion")
    private Set<message> messages;
/*
    @OneToOne(mappedBy= "discussion")
    private event event;*/
}
