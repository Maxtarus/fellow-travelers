package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.Mark;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.repository.MarkRepository;
import ru.sber.fellow_travelers.service.MarkService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;

    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public void save(Mark mark) {
        markRepository.save(mark);
    }

    @Override
    public List<Mark> findAll() {
        return (List<Mark>) markRepository.findAll();
    }

    @Override
    public void rateAfterTrip(User toUser, User fromUser, Trip trip, Mark mark) {
        mark.setToUser(toUser);
        mark.setFromUser(fromUser);
        mark.setTrip(trip);
        save(mark);
    }

    @Override
    public List<Mark> findAllForTripByTripId(long id) {
        return findAll().stream()
                .filter(mark -> mark.getTrip().getId() == id)
                .collect(Collectors.toList());
    }
}
