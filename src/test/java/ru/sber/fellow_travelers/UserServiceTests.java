package ru.sber.fellow_travelers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.repository.UserRepository;
import ru.sber.fellow_travelers.service.impl.UserServiceImpl;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTests {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    User user;

    @BeforeEach
    void setup() {
        user = new User(1L, "qqq", "111");
    }


    @Test
    public void testFindByEmail() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.ofNullable(user));

        User foundUser = userServiceImpl.findByEmail(user.getEmail());

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

//    @Test
//    public void whenUserEmailExists_thenUserShouldBeFound() {
//        when(userService.findByEmail(user.getEmail())).thenReturn(user);
//
//        User foundUser = userService.findByEmail(user.getEmail());
//
//        verify(userService, times(1)).findByEmail(user.getEmail());
//        assertEquals(user.getEmail(), foundUser.getEmail());
//    }
//
//    @Test
//    public void whenUserEmailNotExists_thenThrowException() {
//        when(userService.findByEmail(anyString())).thenThrow(UserNotFoundException.class);
//        assertThrows(UserNotFoundException.class, () -> userService.findByEmail("www"));
//    }
//
//    @Test
//    public void whenUserIdExists_thenUserShouldBeFound() {
//        when(userService.findById(user.getId())).thenReturn(user);
//
//        User foundUser = userService.findById(user.getId());
//
//        verify(userService, times(1)).findById(user.getId());
//        assertEquals(user.getId(), foundUser.getId());
//    }
//
//    @Test
//    public void whenUserIdNotExists_thenUserShouldBeFound() {
//        when(userService.findById(anyLong())).thenThrow(UserNotFoundException.class);
//        assertThrows(UserNotFoundException.class, () -> userService.findById(2L));
//    }

}
