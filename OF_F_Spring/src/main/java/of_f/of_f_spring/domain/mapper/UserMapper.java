package of_f.of_f_spring.domain.mapper;

import of_f.of_f_spring.domain.entity.User;
import of_f.of_f_spring.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);

    User UserDTOTOOFFUser(UserDTO userDTO);

    List<UserDTO> UserTOUserDTO(List<User> users);
}
