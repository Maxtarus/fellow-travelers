package ru.sber.fellow_travelers.mapper;

import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.dto.DriverDTO;
import ru.sber.fellow_travelers.entity.User;
import ru.sber.fellow_travelers.service.DriverService;
import ru.sber.fellow_travelers.util.DateTimeUtils;

@Component
public class DriverMapper {
    private final DriverService driverService;

    public DriverMapper(DriverService driverService) {
        this.driverService = driverService;
    }

    public DriverDTO toDTO(User driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setLastName(driver.getLastName());
        driverDTO.setFirstName(driver.getFirstName());
        driverDTO.setBirthDate(DateTimeUtils.convertToInputFormat(driver.getBirthDate()));
        driverDTO.setAverageMark(driverService.calculateAverageMark(driver));
        driverDTO.setCompletedTripsAmount(driverService.getCompletedTripsAmount(driver));
        return driverDTO;
    }

}
