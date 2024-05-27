//package ru.sber.fellow_travelers.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import ru.sber.fellow_travelers.entity.Review;
//import ru.sber.fellow_travelers.entity.Trip;
//import ru.sber.fellow_travelers.entity.User;
//import ru.sber.fellow_travelers.entity.enums.MarkType;
//import ru.sber.fellow_travelers.entity.enums.TripStatus;
//import ru.sber.fellow_travelers.service.impl.DriverServiceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class DriverServiceTest {
//    private DriverService driverService;
//
//    @BeforeEach
//    void setUp() {
//        driverService = new DriverServiceImpl();
//    }
//
//    @Test
//    void calculateAverageMarkWithNoMarks() {
//        User driver = new User();
//        double result = driverService.calculateAverageMark(driver);
//        assertEquals(0.0, result);
//    }
//
//    @Test
//    void calculateAverageMarkWithMarks() {
//        User driver = new User();
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(new Review(MarkType.EXCELLENT));
//        reviews.add(new Review(MarkType.GOOD));
//        reviews.add(new Review(MarkType.NORMAL));
//        driver.setMarksToUsers(reviews);
//        double result = driverService.calculateAverageMark(driver);
//        assertEquals(4.0, result);
//    }
//
//    @Test
//    void getCompletedTripsAmountWithNoTrips() {
//        User driver = new User();
//        long result = driverService.getCompletedTripsAmount(driver);
//        assertEquals(0, result);
//    }
//
//    @Test
//    void getCompletedTripsAmountWithTrips() {
//        User driver = new User();
//        List<Trip> trips = new ArrayList<>();
//        trips.add(new Trip(1L, 0, TripStatus.COMPLETED, driver));
//        trips.add(new Trip(2L, 2, TripStatus.NOT_COMPLETED, driver));
//        driver.setTrips(trips);
//        long result = driverService.getCompletedTripsAmount(driver);
//        assertEquals(1, result);
//    }
//}