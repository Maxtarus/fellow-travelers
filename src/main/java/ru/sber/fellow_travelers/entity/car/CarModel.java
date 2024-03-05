package ru.sber.fellow_travelers.entity.car;

import jakarta.persistence.*;
import ru.sber.fellow_travelers.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_models")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private CarBrand brand;
    @OneToMany(mappedBy = "model")
    private List<Car> cars = new ArrayList<>();

    public CarModel() { }

    public CarModel(String title, CarBrand brand) {
        this.title = title;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }
}
