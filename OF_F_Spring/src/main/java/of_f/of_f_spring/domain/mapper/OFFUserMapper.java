package of_f.of_f_spring.domain.mapper;

import of_f.of_f_spring.domain.entity.OFFUser;
import of_f.of_f_spring.dto.user.OFFUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OFFUserMapper {
    OFFUserMapper instance = Mappers.getMapper(OFFUserMapper.class);

    @Mapping(source = "userRoleDTOS", target = "userRoles")
    OFFUser OFFUserDTOTOOFFUser(OFFUserDTO target);
}
