package ru.sber.fellow_travelers.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.sber.fellow_travelers.entity.Mark;
import ru.sber.fellow_travelers.entity.Trip;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.entity.enums.MarkType;
import ru.sber.fellow_travelers.entity.enums.TripStatus;
import ru.sber.fellow_travelers.repository.MarkRepository;
import ru.sber.fellow_travelers.service.impl.MarkServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MarkServiceTest {
    @Mock
    private MarkRepository markRepository;
    @InjectMocks
    private MarkServiceImpl markService;

    private User toUser;
    private User fromUser;
    private Trip trip;
    private Mark mark;

    @BeforeEach
    void setUp() {
        toUser = new User(1L, "to@example.com");
        fromUser = new User(2L, "from@example.com");
        trip = new Trip(1L, 0, TripStatus.COMPLETED, new User(3L, "driver@example.com"));
        mark = new Mark(toUser, fromUser, trip, MarkType.EXCELLENT);
    }

    @Test
    void testSaveMark() {
        when(markRepository.save(mark)).thenReturn(mark);

        markService.save(mark);

        verify(markRepository, times(1)).save(mark);
    }

    @Test
    void testFindAllMarks() {
        List<Mark> marks = new ArrayList<>();
        marks.add(mark);

        when(markRepository.findAll()).thenReturn(marks);

        List<Mark> result = markService.findAll();

        assertEquals(marks, result);
    }

    @Test
    void rateAfterTrip() {
        markService.rateAfterTrip(toUser, fromUser, trip, mark);

        verify(markRepository, times(1)).save(mark);

        assertEquals(toUser, mark.getToUser());
        assertEquals(fromUser, mark.getFromUser());
        assertEquals(trip, mark.getTrip());
    }

    @Test
    void findAllForTripByTripId() {
        List<Mark> marks = new ArrayList<>();
        marks.add(mark);
        when(markRepository.findAll()).thenReturn(marks);

        List<Mark> result = markService.findAllForTripByTripId(trip.getId());

        assertEquals(marks, result);
    }
}