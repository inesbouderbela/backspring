package tn.ucar.enicar.info.projetspring.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.scheduling.config.Task;

import java.io.Serializable;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class event implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt = new Date();

    private String organization;
    private Integer participants;
    private String technology;

    @Column(columnDefinition = "TEXT")
    private String benefits; // Format: "Bénéfice 1|Bénéfice 2"

    @Column(columnDefinition = "TEXT")
    private String vision;

    @Lob
    @Column(nullable = true,name = "image_data")
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Poste> postes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "event_volunteers",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> volunteers = new HashSet<>();
    @OneToMany(mappedBy="event")
    private Set<task> tasks;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }


}