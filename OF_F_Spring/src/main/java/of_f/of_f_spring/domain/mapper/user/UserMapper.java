package of_f.of_f_spring.domain.mapper.user;

import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.dto.user.ResUserDTO;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.dto.user.UserSignInDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);

    User UserSignInDTOTOUser(UserSignInDTO userSignInDTO);

    ResUserDTO userTOResUserDTO(User user);

    @Named(value = "userTOUserDTO_N_ROLE")
    @Mapping(target = "userRoles", ignore = true)
    @Mapping(target = "storeOrders",ignore = true)
    UserDTO userTOUserDTO_N_ROLE(User user);

}
