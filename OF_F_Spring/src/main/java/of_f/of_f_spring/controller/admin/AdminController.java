package of_f.of_f_spring.controller.admin;


import of_f.of_f_spring.domain.exception.ApiException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.qr.QRStoreInfoDTO;
import of_f.of_f_spring.service.store.StoreService;
import of_f.of_f_spring.service.user.RoleService;
import of_f.of_f_spring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/app/res") //가맹점 상태 응답
    public ApiResponseDTO resApplication(@RequestParam Long storeId, @RequestParam int status) {
        return storeService.responseApplicationStore(storeId, status);
    }

    @PostMapping("/user/role") //사용자 권한 변경
    public ApiResponseDTO updateUserRole(
            @RequestParam String email,
            @RequestParam Long roleId) {
        return roleService.updateRole(email, roleId);
    }

    @PostMapping("/store/QR/info")
    public ApiResponseDTO storeQRInfo(@RequestParam String status,
                                      @RequestBody QRStoreInfoDTO qrStoreInfoDTO) {

        switch (status) {
            case "insert":
                return storeService.saveStoreQRInfo(qrStoreInfoDTO);
        }

        throw new ApiException(ExceptionEnum.INVALID_PARAMS);
    }

}
