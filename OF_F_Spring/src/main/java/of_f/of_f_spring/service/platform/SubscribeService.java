package of_f.of_f_spring.service.platform;

import of_f.of_f_spring.domain.entity.platform.Subscribe;
import of_f.of_f_spring.domain.mapper.platform.SubscribeMapper;
import of_f.of_f_spring.dto.platform.SubscribeDTO;
import of_f.of_f_spring.repository.platform.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeService {

    @Autowired
    private SubscribeRepository subscribeRepository;

    public Subscribe saveSubscribe(SubscribeDTO subscribeDTO) { // subscribe 저장
        Subscribe subscribe = SubscribeMapper.instance.SubscribeDTOTOSubscribe(subscribeDTO);
        return subscribeRepository.save(subscribe);
    }

    public List<SubscribeDTO> getListSubscribe() { // subscribe 리스트 가져오기
        List<Subscribe> subscribes = subscribeRepository.findAll();
        return SubscribeMapper.instance.SubscribeTOSubscribeDTO(subscribes);
    }

}
