package of_f.of_f_spring.domain.mapper.platform;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import of_f.of_f_spring.domain.entity.platform.Platform;
import of_f.of_f_spring.domain.entity.platform.PlatformInfo;
import of_f.of_f_spring.domain.entity.platform.Subscribe;
import of_f.of_f_spring.domain.entity.user.Role;
import of_f.of_f_spring.dto.platform.PlatformDTO;
import of_f.of_f_spring.dto.platform.PlatformInfoDTO;
import of_f.of_f_spring.dto.platform.SubscribeDTO;
import of_f.of_f_spring.dto.user.RoleDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-09T15:09:02+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_292 (AdoptOpenJDK)"
)
public class PlatformMapperImpl implements PlatformMapper {

    @Override
    public List<PlatformDTO> PlatformTOPlatformDTO(List<Platform> platforms) {
        if ( platforms == null ) {
            return null;
        }

        List<PlatformDTO> list = new ArrayList<PlatformDTO>( platforms.size() );
        for ( Platform platform : platforms ) {
            list.add( platformToPlatformDTO( platform ) );
        }

        return list;
    }

    @Override
    public Platform PlatformDTOTOPlatform(PlatformDTO platformDTO) {
        if ( platformDTO == null ) {
            return null;
        }

        Platform platform = new Platform();

        platform.setSeq( platformDTO.getSeq() );
        platform.setPlatform( platformDTO.getPlatform() );
        platform.setPlatformInfos( platformInfoDTOListToPlatformInfoList( platformDTO.getPlatformInfos() ) );
        platform.setSubscribes( subscribeDTOListToSubscribeList( platformDTO.getSubscribes() ) );
        platform.setRoles( roleDTOListToRoleList( platformDTO.getRoles() ) );

        return platform;
    }

    protected PlatformInfoDTO platformInfoToPlatformInfoDTO(PlatformInfo platformInfo) {
        if ( platformInfo == null ) {
            return null;
        }

        PlatformInfoDTO platformInfoDTO = new PlatformInfoDTO();

        platformInfoDTO.setSeq( platformInfo.getSeq() );
        platformInfoDTO.setPlatformSeq( platformInfo.getPlatformSeq() );
        platformInfoDTO.setName( platformInfo.getName() );
        platformInfoDTO.setValue( platformInfo.getValue() );

        return platformInfoDTO;
    }

    protected List<PlatformInfoDTO> platformInfoListToPlatformInfoDTOList(List<PlatformInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<PlatformInfoDTO> list1 = new ArrayList<PlatformInfoDTO>( list.size() );
        for ( PlatformInfo platformInfo : list ) {
            list1.add( platformInfoToPlatformInfoDTO( platformInfo ) );
        }

        return list1;
    }

    protected SubscribeDTO subscribeToSubscribeDTO(Subscribe subscribe) {
        if ( subscribe == null ) {
            return null;
        }

        SubscribeDTO subscribeDTO = new SubscribeDTO();

        subscribeDTO.setSeq( subscribe.getSeq() );
        subscribeDTO.setPlatformSeq( subscribe.getPlatformSeq() );
        subscribeDTO.setName( subscribe.getName() );
        subscribeDTO.setPrice( subscribe.getPrice() );

        return subscribeDTO;
    }

    protected List<SubscribeDTO> subscribeListToSubscribeDTOList(List<Subscribe> list) {
        if ( list == null ) {
            return null;
        }

        List<SubscribeDTO> list1 = new ArrayList<SubscribeDTO>( list.size() );
        for ( Subscribe subscribe : list ) {
            list1.add( subscribeToSubscribeDTO( subscribe ) );
        }

        return list1;
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

    protected List<RoleDTO> roleListToRoleDTOList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDTO> list1 = new ArrayList<RoleDTO>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleDTO( role ) );
        }

        return list1;
    }

    protected PlatformDTO platformToPlatformDTO(Platform platform) {
        if ( platform == null ) {
            return null;
        }

        PlatformDTO platformDTO = new PlatformDTO();

        platformDTO.setSeq( platform.getSeq() );
        platformDTO.setPlatform( platform.getPlatform() );
        platformDTO.setPlatformInfos( platformInfoListToPlatformInfoDTOList( platform.getPlatformInfos() ) );
        platformDTO.setSubscribes( subscribeListToSubscribeDTOList( platform.getSubscribes() ) );
        platformDTO.setRoles( roleListToRoleDTOList( platform.getRoles() ) );

        return platformDTO;
    }

    protected PlatformInfo platformInfoDTOToPlatformInfo(PlatformInfoDTO platformInfoDTO) {
        if ( platformInfoDTO == null ) {
            return null;
        }

        PlatformInfo platformInfo = new PlatformInfo();

        platformInfo.setSeq( platformInfoDTO.getSeq() );
        platformInfo.setPlatformSeq( platformInfoDTO.getPlatformSeq() );
        platformInfo.setName( platformInfoDTO.getName() );
        platformInfo.setValue( platformInfoDTO.getValue() );

        return platformInfo;
    }

    protected List<PlatformInfo> platformInfoDTOListToPlatformInfoList(List<PlatformInfoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<PlatformInfo> list1 = new ArrayList<PlatformInfo>( list.size() );
        for ( PlatformInfoDTO platformInfoDTO : list ) {
            list1.add( platformInfoDTOToPlatformInfo( platformInfoDTO ) );
        }

        return list1;
    }

    protected Subscribe subscribeDTOToSubscribe(SubscribeDTO subscribeDTO) {
        if ( subscribeDTO == null ) {
            return null;
        }

        Subscribe subscribe = new Subscribe();

        subscribe.setSeq( subscribeDTO.getSeq() );
        subscribe.setPlatformSeq( subscribeDTO.getPlatformSeq() );
        subscribe.setName( subscribeDTO.getName() );
        subscribe.setPrice( subscribeDTO.getPrice() );

        return subscribe;
    }

    protected List<Subscribe> subscribeDTOListToSubscribeList(List<SubscribeDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Subscribe> list1 = new ArrayList<Subscribe>( list.size() );
        for ( SubscribeDTO subscribeDTO : list ) {
            list1.add( subscribeDTOToSubscribe( subscribeDTO ) );
        }

        return list1;
    }

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setSeq( roleDTO.getSeq() );
        role.setPlatformSeq( roleDTO.getPlatformSeq() );
        role.setRoleName( roleDTO.getRoleName() );

        return role;
    }

    protected List<Role> roleDTOListToRoleList(List<RoleDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleDTO roleDTO : list ) {
            list1.add( roleDTOToRole( roleDTO ) );
        }

        return list1;
    }
}
