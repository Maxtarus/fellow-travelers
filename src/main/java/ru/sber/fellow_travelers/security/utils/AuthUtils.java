package ru.sber.fellow_travelers.security.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sber.fellow_travelers.entity.User;

public final class AuthUtils {
    private AuthUtils() { }
    public static User getUserFromContext() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return (User) user;
    }
}
