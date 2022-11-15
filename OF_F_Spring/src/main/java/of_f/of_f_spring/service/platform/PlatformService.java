package of_f.of_f_spring.service.platform;

import of_f.of_f_spring.domain.entity.Platform;
import of_f.of_f_spring.domain.mapper.PlatformMapper;
import of_f.of_f_spring.dto.user.PlatformDTO;
import of_f.of_f_spring.repository.platform.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlatformService {
    @Autowired
    private PlatformRepository platformRepository;

    public List<PlatformDTO> getPlatform() { // platform 리스트 가져오기

        List<Platform> platforms = platformRepository.findAll();

        List<PlatformDTO> platformDTOS = PlatformMapper.instance.PlatformTOPlatformDTO(platforms);

        return platformDTOS;
    }

    public Platform savePlatform(PlatformDTO platformDTO) { //platform 저장
        Platform platform = PlatformMapper.instance.PlatformDTOTOPlatform(platformDTO);
        return platformRepository.save(platform);
    }

}
