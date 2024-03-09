package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import ru.sber.fellow_travelers.entity.enums.RoleType;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private RoleType title;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() { }

    public Role(long id, RoleType title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + title;
    }

    public Long getId() {
        return id;
    }

    public RoleType getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(RoleType title) {
        this.title = title;
    }
}
