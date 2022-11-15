package of_f.of_f_spring.domain.mapper;

import of_f.of_f_spring.domain.entity.User;
import of_f.of_f_spring.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userRoleDTOS", target = "userRoles")
    User OFFUserDTOTOOFFUser(UserDTO target);
}
