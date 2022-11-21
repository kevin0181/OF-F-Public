package of_f.of_f_spring.domain.mapper.platform;

import of_f.of_f_spring.domain.entity.platform.Subscribe;
import of_f.of_f_spring.dto.platform.SubscribeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubscribeMapper {
    SubscribeMapper instance = Mappers.getMapper(SubscribeMapper.class);

    Subscribe SubscribeDTOTOSubscribe(SubscribeDTO subscribeDTO);

    List<SubscribeDTO> SubscribeTOSubscribeDTO(List<Subscribe> subscribes);
}
