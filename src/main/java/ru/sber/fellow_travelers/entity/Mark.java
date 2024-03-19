package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import ru.sber.fellow_travelers.entity.enums.MarkType;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    private User toUser;
    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    private User fromUser;
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MarkType markType;

    public Mark() { }

    public Mark(User toUser, User fromUser, Trip trip, MarkType markType) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.trip = trip;
        this.markType = markType;
    }

    public Long getId() {
        return id;
    }

    public User getToUser() {
        return toUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public MarkType getMark() {
        return markType;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public MarkType getMarkType() {
        return markType;
    }

    public void setMarkType(MarkType markType) {
        this.markType = markType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public void setMark(MarkType markType) {
        this.markType = markType;
    }

}
