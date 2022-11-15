package of_f.of_f_spring.domain.mapper;

import of_f.of_f_spring.domain.entity.Platform;
import of_f.of_f_spring.domain.entity.Subscribe;
import of_f.of_f_spring.dto.user.PlatformDTO;
import of_f.of_f_spring.dto.user.SubscribeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubscribeMapper {
    SubscribeMapper instance = Mappers.getMapper(SubscribeMapper.class);

    Subscribe SubscribeDTOTOSubscribe(SubscribeDTO subscribeDTO);

    List<SubscribeDTO> SubscribeTOSubscribeDTO(List<Subscribe> subscribes);
}
