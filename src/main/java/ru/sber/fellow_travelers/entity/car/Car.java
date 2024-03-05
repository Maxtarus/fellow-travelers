package ru.sber.fellow_travelers.entity.car;

import jakarta.persistence.*;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.CarBody;
import ru.sber.fellow_travelers.entity.enums.Color;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;
    @Enumerated(EnumType.STRING)
    @Column(name = "body", nullable = false)
    private CarBody body;
    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;
    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel model;

    public Car() { }

    public Car(String licensePlate, CarBody body, Color color, Integer releaseYear, User driver, CarModel model) {
        this.licensePlate = licensePlate;
        this.body = body;
        this.color = color;
        this.releaseYear = releaseYear;
        this.driver = driver;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public CarBody getBody() {
        return body;
    }

    public Color getColor() {
        return color;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public User getDriver() {
        return driver;
    }

    public CarModel getModel() {
        return model;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setBody(CarBody body) {
        this.body = body;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }
}
