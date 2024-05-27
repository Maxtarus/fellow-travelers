//package ru.sber.fellow_travelers.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import ru.sber.fellow_travelers.entity.Review;
//import ru.sber.fellow_travelers.entity.Trip;
//import ru.sber.fellow_travelers.entity.User;
//import ru.sber.fellow_travelers.entity.enums.MarkType;
//import ru.sber.fellow_travelers.entity.enums.TripStatus;
//import ru.sber.fellow_travelers.repository.MarkRepository;
//import ru.sber.fellow_travelers.service.impl.MarkServiceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class ReviewServiceTest {
//    @Mock
//    private MarkRepository markRepository;
//    @InjectMocks
//    private MarkServiceImpl markService;
//
//    private User toUser;
//    private User fromUser;
//    private Trip trip;
//    private Review review;
//
//    @BeforeEach
//    void setUp() {
//        toUser = new User(1L, "to@example.com");
//        fromUser = new User(2L, "from@example.com");
//        trip = new Trip(1L, 0, TripStatus.COMPLETED, new User(3L, "driver@example.com"));
//        review = new Review(toUser, fromUser, trip, MarkType.EXCELLENT);
//    }
//
//    @Test
//    void testSaveMark() {
//        when(markRepository.save(review)).thenReturn(review);
//
//        markService.save(review);
//
//        verify(markRepository, times(1)).save(review);
//    }
//
//    @Test
//    void testFindAllMarks() {
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(review);
//
//        when(markRepository.findAll()).thenReturn(reviews);
//
//        List<Review> result = markService.findAll();
//
//        assertEquals(reviews, result);
//    }
//
//    @Test
//    void rateAfterTrip() {
//        markService.rateAfterTrip(toUser, fromUser, trip, review);
//
//        verify(markRepository, times(1)).save(review);
//
//        assertEquals(toUser, review.getToUser());
//        assertEquals(fromUser, review.getFromUser());
//        assertEquals(trip, review.getTrip());
//    }
//
//    @Test
//    void findAllForTripByTripId() {
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(review);
//        when(markRepository.findAll()).thenReturn(reviews);
//
//        List<Review> result = markService.findAllForTripByTripId(trip.getId());
//
//        assertEquals(reviews, result);
//    }
//}