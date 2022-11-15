package of_f.of_f_spring.service.platform;

import of_f.of_f_spring.dto.user.PlatformDTO;
import of_f.of_f_spring.dto.user.SubscribeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlatformServiceTest {

    @Autowired
    private PlatformService platformService;

    @Autowired
    private SubscribeService subscribeService;

    @Test
    public void getListPlatformTest() { // platform 리스트 가져오기
        platformService.getPlatform();
    }

    @Test
    public void savePlatform() { // platform 저장
        PlatformDTO platformDTO = new PlatformDTO();
        platformDTO.setPlatform("QR_Tech");
        platformService.savePlatform(platformDTO);
    }

    @Test
    public void saveSubscribe() { // subscribe 저장
        SubscribeDTO subscribeDTO = new SubscribeDTO();
        subscribeDTO.setPlatformSeq(1L);
        subscribeDTO.setName("구독1");
        subscribeDTO.setPrice("1000");
        subscribeService.saveSubscribe(subscribeDTO);
    }

    @Test
    public void getListSubscribe() { // subscribe 가져오기
        subscribeService.getListSubscribe();
    }

}
