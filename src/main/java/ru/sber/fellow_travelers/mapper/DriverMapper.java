package ru.sber.fellow_travelers.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.dto.DriverDTO;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.service.DriverService;

@Component
@RequiredArgsConstructor
public class DriverMapper {
    private final DriverService driverService;

    public DriverDTO toDTO(User driver) {
        return DriverDTO.builder()
                .lastName(driver.getLastName())
                .firstName(driver.getFirstName())
                .age(driverService.calculateAge(driver))
                .averageMark(driverService.calculateAverageMark(driver))
                .completedTripsAmount(driverService.getCompletedTripsAmount(driver))
                .build();
    }

}
