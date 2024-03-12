package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sber.fellow_travelers.entity.car.Car;
import ru.sber.fellow_travelers.entity.enums.RoleType;
import ru.sber.fellow_travelers.util.LocalDateUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "toUser")
    private List<Feedback> toUsersFeedbacks = new ArrayList<>();
    @OneToMany(mappedBy = "fromUser")
    private List<Feedback> fromUsersFeedbacks = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "passenger")
    private List<Request> requests = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    private List<Trip> trips = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    private List<Car> cars = new ArrayList<>();

    public User() { }

    public User(long id, String email, String password, String firstName,
                String lastName, String phoneNumber, String birthDate, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = LocalDate.parse(birthDate);
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    private List<RoleType> getUserRoleTypes() {
        return roles.stream()
                .map(Role::getType)
                .collect(Collectors.toList());
    }

    public boolean isAdmin() {
        return getUserRoleTypes().contains(RoleType.ADMIN);
    }

    public boolean isDriver() {
        return getUserRoleTypes().contains(RoleType.DRIVER);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = LocalDateUtils.convertToISO(birthDate);
    }
}
