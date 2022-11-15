package of_f.of_f_spring.domain.mapper;

import of_f.of_f_spring.domain.entity.Platform;
import of_f.of_f_spring.dto.user.PlatformDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlatformMapper {
    PlatformMapper instance = Mappers.getMapper(PlatformMapper.class);

    @Mapping(source = "platformInfos", target = "platformInfoDTOs")
    @Mapping(source = "subscribes", target = "subscribeDTOs")
    @Mapping(source = "roles", target = "roleDTOs")
    List<PlatformDTO> PlatformTOPlatformDTO(List<Platform> target);

    @Mapping(source = "platformInfoDTOs", target = "platformInfos")
    @Mapping(source = "subscribeDTOs", target = "subscribes")
    @Mapping(source = "roleDTOs", target = "roles")
    Platform PlatformDTOTOPlatform(PlatformDTO target);

}
