package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.Review;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.MarkType;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.service.DriverService;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

@Service
public class DriverServiceImpl implements DriverService {

    @Override
    public String calculateAverageMark(User driver) {
        double averageMark = driver.getMarksToUsers()
                .stream()
                .map(Review::getMarkType)
                .map(MarkType::getDigitMark)
                .mapToDouble(mark -> mark)
                .average()
                .orElse(0.0);
        return new DecimalFormat("#0.00").format(averageMark);
    }

    @Override
    public long getCompletedTripsAmount(User driver) {
        return driver.getTrips()
                .stream()
                .filter(trip -> trip.getTripStatus().equals(TripStatus.COMPLETED))
                .count();
    }

    @Override
    public int calculateAge(User driver) {
        return Period.between(driver.getBirthDate(), LocalDate.now()).getYears();
    }
}
