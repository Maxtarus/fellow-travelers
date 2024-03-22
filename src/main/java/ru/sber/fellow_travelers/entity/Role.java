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
    @Column(name = "type")
    private RoleType type;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() { }

    public Role(long id, RoleType type) {
        this.id = id;
        this.type = type;
    }

    public Role(RoleType type) {
        this.type = type;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + type;
    }

    public Long getId() {
        return id;
    }

    public RoleType getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(RoleType type) {
        this.type = type;
    }
}
