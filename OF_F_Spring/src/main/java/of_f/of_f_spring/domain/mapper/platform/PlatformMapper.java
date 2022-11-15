package of_f.of_f_spring.domain.mapper.platform;

import of_f.of_f_spring.domain.entity.platform.Platform;
import of_f.of_f_spring.dto.platform.PlatformDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlatformMapper {
    PlatformMapper instance = Mappers.getMapper(PlatformMapper.class);

    @Mapping(source = "platformInfos", target = "platformInfoDTOS")
    @Mapping(source = "subscribes", target = "subscribeDTOS")
    @Mapping(source = "roles", target = "roleDTOS")
    List<PlatformDTO> PlatformTOPlatformDTO(List<Platform> platforms);

    Platform PlatformDTOTOPlatform(PlatformDTO platformDTO);
}
