package of_f.of_f_spring.controller.user;

import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/app/req") // 가맹점 신청
    public ApiResponseDTO putAppNewStore(@RequestBody @Valid StoreDTO storeDTO, Principal principal) {
        return storeService.applicationNewStore(storeDTO, principal);
    }

}
