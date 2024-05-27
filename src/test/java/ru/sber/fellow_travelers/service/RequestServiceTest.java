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
//import ru.sber.fellow_travelers.entity.Request;
//import ru.sber.fellow_travelers.entity.Trip;
//import ru.sber.fellow_travelers.entity.User;
//import ru.sber.fellow_travelers.entity.enums.RequestStatus;
//import ru.sber.fellow_travelers.entity.enums.TripStatus;
//import ru.sber.fellow_travelers.exception.RequestNotFoundException;
//import ru.sber.fellow_travelers.repository.RequestRepository;
//import ru.sber.fellow_travelers.service.impl.RequestServiceImpl;
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
//class RequestServiceTest {
//    @Mock
//    private RequestRepository requestRepository;
//    @InjectMocks
//    private RequestServiceImpl requestService;
//
//    private User passenger1;
//    private Trip trip;
//    private Request request1;
//    private Request request2;
//
//    @BeforeEach
//    void setUp() {
//        passenger1 = new User(1L, "passenger1@example.com");
//        User testPassenger2 = new User(2L, "passenger2@example.com");
//        User testDriver = new User(3L, "driver@example.com");
//        trip = new Trip(1L, 2, TripStatus.NOT_COMPLETED, testDriver);
//        request1 = new Request(1L, RequestStatus.UNDER_CONSIDERATION, passenger1, trip);
//        request2 = new Request(2L, RequestStatus.UNDER_CONSIDERATION, testPassenger2, trip);
//    }
//
//    @Test
//    void saveRequest() {
//        when(requestRepository.save(request1)).thenReturn(request1);
//
//        requestService.save(request1);
//
//        verify(requestRepository, times(1)).save(request1);
//    }
//
//    @Test
//    void findAllRequests() {
//        List<Request> requests = new ArrayList<>();
//        requests.add(request1);
//        when(requestRepository.findAll()).thenReturn(requests);
//
//        List<Request> result = requestService.findAll();
//
//        assertEquals(requests, result);
//    }
//
//    @Test
//    void findById() {
//        when(requestRepository.findById(1L)).thenReturn(Optional.of(request1));
//
//        Request result = requestService.findById(1L);
//
//        assertEquals(request1, result);
//    }
//
//    @Test
//    void findAllByPassengerId() {
//        when(requestRepository.findAll()).thenReturn(List.of(request1));
//
//        List<Request> result = requestService.findAllByPassengerId(passenger1.getId());
//
//        assertEquals(1, result.size());
//        assertTrue(result.contains(request1));
//    }
//
//    @Test
//    void findAvailableByDriverId() {
//        List<Request> requests = new ArrayList<>();
//        requests.add(request1);
//        requests.add(request2);
//        when(requestRepository.findAll()).thenReturn(requests);
//
//        List<Request> result = requestService.findAvailableByDriverId(trip.getDriver().getId());
//
//        assertEquals(requests, result);
//    }
//
//    @Test
//    void deleteById() {
//        willDoNothing().given(requestRepository).deleteById(request1.getId());
//
//        requestService.deleteById(request1.getId());
//
//        verify(requestRepository, times(1)).deleteById(request1.getId());
//    }
//
//    @Test
//    void createRequest() {
//        requestService.createRequest(passenger1, trip);
//        verify(requestRepository, times(1)).save(any(Request.class));
//    }
//
//    @Test
//    void approveRequest() {
//        requestService.approveRequest(request1);
//
//        assertEquals(RequestStatus.APPROVED, request1.getRequestStatus());
//        verify(requestRepository, times(1)).save(request1);
//    }
//
//    @Test
//    void disapproveRequest() {
//        requestService.disapproveRequest(request1);
//
//        assertEquals(RequestStatus.REJECTED, request1.getRequestStatus());
//        verify(requestRepository, times(1)).save(request1);
//    }
//
//    @Test
//    void findAllApprovedForCompletedTripsByPassenger() {
//        List<Request> requests = new ArrayList<>();
//        request1.setRequestStatus(RequestStatus.APPROVED);
//        requests.add(request1);
//
//        when(requestRepository.findAllByPassengerId(passenger1.getId(), TripStatus.COMPLETED, RequestStatus.APPROVED))
//                .thenReturn(requests);
//
//        List<Request> result = requestService.findAllApprovedForCompletedTripsByPassenger(passenger1);
//
//        assertEquals(requests, result);
//    }
//
//
//
//    @Test
//    void testFindById_shouldThrowRequestNotFoundException() {
//        when(requestRepository.findById(request1.getId())).thenReturn(Optional.empty());
//        assertThrows(RequestNotFoundException.class, () -> requestService.findById(request1.getId()));
//    }
//}