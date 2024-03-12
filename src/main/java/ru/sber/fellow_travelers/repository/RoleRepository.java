package ru.sber.fellow_travelers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sber.fellow_travelers.entity.Role;
import ru.sber.fellow_travelers.entity.enums.RoleType;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByType(RoleType type);
}
