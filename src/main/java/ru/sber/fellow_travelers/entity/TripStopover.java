package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trip_stopovers")
public class TripStopover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "point", nullable = false)
    private String point;
    @Column(name = "price", nullable = false)
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
