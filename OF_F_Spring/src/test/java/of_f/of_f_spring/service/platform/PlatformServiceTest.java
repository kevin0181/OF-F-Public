package of_f.of_f_spring.service.platform;

import of_f.of_f_spring.dto.user.PlatformDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlatformServiceTest {

    @Autowired
    private PlatformService platformService;

    @Test
    public void getListPlatformTest() {
        platformService.getPlatform();
    }

    @Test
    public void savePlatform() {
        PlatformDTO platformDTO = new PlatformDTO();
        platformDTO.setPlatform("QR_Tech");
        platformService.savePlatform(platformDTO);
    }

}
