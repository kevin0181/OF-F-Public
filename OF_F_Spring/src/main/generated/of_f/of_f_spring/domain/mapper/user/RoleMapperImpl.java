package of_f.of_f_spring.domain.mapper.user;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import of_f.of_f_spring.domain.entity.user.Role;
import of_f.of_f_spring.dto.user.RoleDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-12T20:01:18+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_292 (AdoptOpenJDK)"
)
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role RoleDTOTORole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setSeq( roleDTO.getSeq() );
        role.setPlatformSeq( roleDTO.getPlatformSeq() );
        role.setRoleName( roleDTO.getRoleName() );

        return role;
    }

    @Override
    public List<RoleDTO> RolesTORoleDTOs(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( roles.size() );
        for ( Role role : roles ) {
            list.add( roleToRoleDTO( role ) );
        }

        return list;
    }

    @Override
    public List<Role> RoleDTOTORole(List<RoleDTO> roleDTOS) {
        if ( roleDTOS == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( roleDTOS.size() );
        for ( RoleDTO roleDTO : roleDTOS ) {
            list.add( RoleDTOTORole( roleDTO ) );
        }

        return list;
    }

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setSeq( role.getSeq() );
        roleDTO.setPlatformSeq( role.getPlatformSeq() );
        roleDTO.setRoleName( role.getRoleName() );

        return roleDTO;
    }
}
