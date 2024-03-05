package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import ru.sber.fellow_travelers.entity.enums.RoleType;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType role;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() { }

    public Role(RoleType role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public RoleType getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
