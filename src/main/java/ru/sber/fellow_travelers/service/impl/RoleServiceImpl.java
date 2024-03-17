package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.Role;
import ru.sber.fellow_travelers.entity.enums.RoleType;
import ru.sber.fellow_travelers.repository.RoleRepository;
import ru.sber.fellow_travelers.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleType> findAllTypes() {
        List<Role> roles = (List<Role>) roleRepository.findAll();
        return roles.stream()
                .map(Role::getType)
                .collect(Collectors.toList());
    }

    @Override
    public Role findByType(RoleType type) {
        return roleRepository.findByType(type);
    }
}
