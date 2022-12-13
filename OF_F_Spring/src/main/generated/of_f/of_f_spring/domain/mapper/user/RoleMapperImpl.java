package of_f.of_f_spring.domain.mapper.user;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import of_f.of_f_spring.domain.entity.user.Role;
import of_f.of_f_spring.dto.user.RoleDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-13T16:15:23+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_292 (AdoptOpenJDK)"
)
public class RoleMapperImpl implements RoleMapper {

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
