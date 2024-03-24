package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.Mark;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.MarkType;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

    @Override
    public double calculateAverageMark(User driver) {
        return driver.getMarksToUsers()
                .stream()
                .map(Mark::getMarkType)
                .map(MarkType::getDigitMark)
                .mapToDouble(mark -> mark)
                .average()
                .orElse(0.0);
    }

    @Override
    public long getCompletedTripsAmount(User driver) {
        return driver.getTrips()
                .stream()
                .filter(trip -> trip.getStatus().equals(TripStatus.COMPLETED))
                .count();
    }
}
