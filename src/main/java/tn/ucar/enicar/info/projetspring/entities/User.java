package tn.ucar.enicar.info.projetspring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

    public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private int score ;

    @OneToMany(mappedBy = "user")
    private List<Token>tokens;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<task> tasks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<notification> notifications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<message> messages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Candidate> candidates;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
