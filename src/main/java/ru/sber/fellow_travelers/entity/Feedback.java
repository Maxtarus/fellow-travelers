package ru.sber.fellow_travelers.entity;

import jakarta.persistence.*;
import ru.sber.fellow_travelers.entity.enums.Mark;

@Entity
@Table(name = "feedback")
public class Feedback {
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
    @Enumerated(EnumType.STRING)
    @Column(name = "mark", nullable = false)
    private Mark mark;
    @Column(name = "comment")
    private String comment;

    public Feedback() { }

    public Feedback(User toUser, User fromUser, Mark mark, String comment) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.mark = mark;
        this.comment = comment;
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

    public Mark getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
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

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
