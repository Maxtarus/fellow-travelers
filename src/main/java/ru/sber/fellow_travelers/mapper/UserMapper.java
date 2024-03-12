package ru.sber.fellow_travelers.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sber.fellow_travelers.dto.UserDTO;
import ru.sber.fellow_travelers.entity.User;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    public UserMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public User toEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    public UserDTO toDTO(User userEntity) {
        return mapper.map(userEntity, UserDTO.class);
    }
}
