package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import ru.sber.fellow_travelers.entity.enums.RequestStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@Entity
@Table(name = "requests")
@ToString(exclude = {"passenger", "trip"})
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus requestStatus;
    @Column(name = "passengers_number", nullable = false)
    private Integer passengersNumber;
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
}
