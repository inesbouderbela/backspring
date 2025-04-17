package tn.ucar.enicar.info.projetspring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.scheduling.config.Task;

import java.io.Serializable;
import java.util.*;
@Data
@Entity
@Getter @Setter
@NoArgsConstructor
public class event implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotBlank(message = "Le lieu est obligatoire")
    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    @Future(message = "La date doit être dans le futur")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Future(message = "La date doit être dans le futur")
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt = new Date();
    private String organization;
    private Integer participants;
    @Column(columnDefinition = "TEXT")
    private String technology;
    @Column(columnDefinition = "TEXT")
    private String benefits; // Format: "Bénéfice 1|Bénéfice 2"
    @Column(columnDefinition = "TEXT")
    private String vision;

    @Column(nullable = true)
    private String imagePath;


    @ManyToOne
    @JoinColumn(name = "general_coordinator_id")  // Nom de colonne cohérent
    @JsonBackReference
    private User generalCoordinator;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Poste> postes = new ArrayList<>();



    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Team> teams = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.participants = 0;
    }

    @Transient
    @JsonProperty("imageUrl")
    public String getImageUrl() {
        if (imagePath == null) return null;
        return "/SpringMVC/api/uploads/" + imagePath;
    }



}