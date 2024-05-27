package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.sber.fellow_travelers.entity.enums.TripStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trips")
@ToString(exclude = {"driver", "requests", "reviews", "tripStopovers" })
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "start_point", nullable = false)
    private String startPoint;
    @Column(name = "final_point", nullable = false)
    private String finalPoint;
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;
    @Column(name = "free_seats", nullable = false)
    private Integer freeSeats;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TripStatus tripStatus;
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "trip")
    private List<Request> requests = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "trip")
    private List<Review> reviews = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "trip")
    private List<TripStopover> tripStopovers = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_details_id", referencedColumnName = "id")
    private TripDetails tripDetails;
}
