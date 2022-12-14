package of_f.of_f_spring.domain.mapper.platform;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import of_f.of_f_spring.domain.entity.platform.Subscribe;
import of_f.of_f_spring.dto.platform.SubscribeDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-14T16:23:05+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_292 (AdoptOpenJDK)"
)
public class SubscribeMapperImpl implements SubscribeMapper {

    @Override
    public Subscribe SubscribeDTOTOSubscribe(SubscribeDTO subscribeDTO) {
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

    @Override
    public List<SubscribeDTO> SubscribeTOSubscribeDTO(List<Subscribe> subscribes) {
        if ( subscribes == null ) {
            return null;
        }

        List<SubscribeDTO> list = new ArrayList<SubscribeDTO>( subscribes.size() );
        for ( Subscribe subscribe : subscribes ) {
            list.add( subscribeToSubscribeDTO( subscribe ) );
        }

        return list;
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
}
