package of_f.of_f_spring.controller.user;


import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/app/res") //가맹점 상태 응답
    public ApiResponseDTO resApplication(@RequestParam Long storeId, @RequestParam int status) {
        return storeService.responseApplicationStore(storeId, status);
    }
}
