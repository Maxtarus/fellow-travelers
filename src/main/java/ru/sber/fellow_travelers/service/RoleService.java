package ru.sber.fellow_travelers.service;

import ru.sber.fellow_travelers.entity.Role;
import ru.sber.fellow_travelers.entity.enums.RoleType;

import java.util.List;

public interface RoleService {
    List<RoleType> findAllTypes();
    Role findByType(RoleType type);
}
