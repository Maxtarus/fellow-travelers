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
//import ru.sber.fellow_travelers.entity.Trip;
//import ru.sber.fellow_travelers.entity.User;
//import ru.sber.fellow_travelers.entity.enums.TripStatus;
//import ru.sber.fellow_travelers.exception.TripNotFoundException;
//import ru.sber.fellow_travelers.repository.TripRepository;
//import ru.sber.fellow_travelers.service.impl.TripServiceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.willDoNothing;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class TripServiceTest {
//    @Mock
//    private TripRepository tripRepository;
//    @InjectMocks
//    private TripServiceImpl tripService;
//
//    private User driver;
//    private User passenger;
//
//    private Trip notCompletedTripWithFreeSeats;
//    private Trip notCompletedTripWithNoSeats;
//    private Trip completedTrip;
//
//
//    @BeforeEach
//    void setUp() {
//        driver = new User(1L, "driver@example.com");
//        passenger = new User(2L, "passenger@example.com");
//        notCompletedTripWithFreeSeats = new Trip(1L, 2, TripStatus.NOT_COMPLETED, driver);
//        notCompletedTripWithNoSeats = new Trip(2L, 0, TripStatus.NOT_COMPLETED, driver);
//        completedTrip = new Trip(3L, 0, TripStatus.COMPLETED, driver);
//    }
//
//    @Test
//    void testSaveTrip() {
//        when(tripRepository.save(notCompletedTripWithFreeSeats)).thenReturn(notCompletedTripWithFreeSeats);
//
//        tripService.save(notCompletedTripWithFreeSeats);
//
//        verify(tripRepository, times(1)).save(notCompletedTripWithFreeSeats);
//    }
//
//    @Test
//    void testFindAllNotCompletedByDriverId_shouldReturnNotEmptyList() {
//        when(tripRepository.findAllByDriverId(notCompletedTripWithFreeSeats.getId()))
//                .thenReturn(List.of(notCompletedTripWithNoSeats, notCompletedTripWithFreeSeats));
//
//        List<Trip> result = tripService.findAllNotCompletedByDriverId(notCompletedTripWithFreeSeats.getId());
//
//        assertEquals(2, result.size());
//        assertFalse(result.contains(completedTrip));
//    }
//
//    @Test
//    void testFindAllNotCompletedByDriverId_shouldReturnEmptyList() {
//        when(tripRepository.findAllByDriverId(notCompletedTripWithNoSeats.getId())).thenReturn(List.of());
//
//        List<Trip> result = tripService.findAllNotCompletedByDriverId(notCompletedTripWithNoSeats.getId());
//
//        assertTrue(result.isEmpty());
//    }
//
////    @Test
////    void testFindAllAvailableForPassenger() {
////        List<Trip> allTrips = new ArrayList<>();
////        allTrips.add(notCompletedTripWithFreeSeats);
////        allTrips.add(notCompletedTripWithNoSeats);
////        allTrips.add(completedTrip);
////        when(tripRepository.findAll()).thenReturn(allTrips);
////
////        List<Trip> result = tripService.findAllAvailableForPassenger(passenger);
////
////        assertEquals(1, result.size());
////        assertTrue(result.contains(notCompletedTripWithFreeSeats));
////    }
//
//    @Test
//    void testDeleteById() {
//        willDoNothing().given(tripRepository).deleteById(notCompletedTripWithFreeSeats.getId());
//
//        tripService.deleteById(notCompletedTripWithFreeSeats.getId());
//
//        verify(tripRepository, times(1)).deleteById(notCompletedTripWithFreeSeats.getId());
//    }
//
//    @Test
//    void testFindById_shouldReturnTrip() {
//        when(tripRepository.findById(notCompletedTripWithFreeSeats.getId()))
//                .thenReturn(Optional.ofNullable(notCompletedTripWithFreeSeats));
//
//        Trip trip = tripService.findById(notCompletedTripWithFreeSeats.getId());
//
//        assertEquals(notCompletedTripWithFreeSeats, trip);
//    }
//
//    @Test
//    void testFindById_shouldReturnOptionalEmpty() {
//        when(tripRepository.findById(notCompletedTripWithFreeSeats.getId())).thenReturn(Optional.empty());
//        assertThrows(TripNotFoundException.class, () -> tripService.findById(notCompletedTripWithFreeSeats.getId()));
//    }
//
//    @Test
//    void testDecrementFreeSeats() {
//        tripService.decrementFreeSeats(notCompletedTripWithFreeSeats);
//        assertEquals(1, notCompletedTripWithFreeSeats.getFreeSeats());
//    }
//
//    @Test
//    void testCompleteTrip() {
//        tripService.completeTrip(notCompletedTripWithFreeSeats);
//        assertEquals(TripStatus.COMPLETED, notCompletedTripWithFreeSeats.getTripStatus());
//    }
//
//    @Test
//    void testFindAllCompletedByDriverId() {
//        List<Trip> trips = new ArrayList<>();
//        trips.add(completedTrip);
//        when(tripRepository.findAllByDriverId(driver.getId())).thenReturn(trips);
//
//        List<Trip> result = tripService.findAllCompletedByDriverId(driver.getId());
//
//        assertEquals(trips, result);
//    }
//}