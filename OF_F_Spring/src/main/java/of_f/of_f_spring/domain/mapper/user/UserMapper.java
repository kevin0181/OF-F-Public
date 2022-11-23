package of_f.of_f_spring.domain.mapper.user;

import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.dto.user.UserSignInDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);

    User UserSignInDTOTOUser(UserSignInDTO userSignInDTO);
    UserDTO userTOUserDTO(User user);

    @Mapping(target = "store.user", ignore = true)
    List<UserDTO> UserTOUserDTO(List<User> users);
}
