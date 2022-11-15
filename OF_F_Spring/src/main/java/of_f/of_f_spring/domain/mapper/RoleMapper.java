package of_f.of_f_spring.domain.mapper;

import of_f.of_f_spring.domain.entity.Role;
import of_f.of_f_spring.dto.user.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper instance = Mappers.getMapper(RoleMapper.class);

    Role RoleDTOTORole(RoleDTO roleDTO);

    List<RoleDTO> RolesTORoleDTOs(List<Role> roles);

    List<Role> RoleDTOTORole(List<RoleDTO> roleDTOS);
}
