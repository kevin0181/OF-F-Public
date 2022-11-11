package of_f.of_f_spring.domain.mapper;

import of_f.of_f_spring.domain.entity.Role;
import of_f.of_f_spring.dto.user.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper instance = Mappers.getMapper(RoleMapper.class);

    Role RoleDTOTORole(RoleDTO target);

    List<RoleDTO> RolesTORoleDTOs(List<Role> target);
}
