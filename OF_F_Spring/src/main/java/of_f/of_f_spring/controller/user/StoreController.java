package of_f.of_f_spring.controller.user;

import of_f.of_f_spring.domain.exception.ApiException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.menu.StoreCategoryDTO;
import of_f.of_f_spring.dto.store.menu.StoreMenuDTO;
import of_f.of_f_spring.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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

    @GetMapping("/admin") // 가맹점 정보 (관리자용)
    public ApiResponseDTO getStoreInfo(Principal principal) {
        return storeService.getStoreInfoAdmin(principal);
    }

    @PostMapping("/admin/category")
    public ApiResponseDTO category(@RequestParam String status,
                                   @RequestBody @Valid StoreCategoryDTO storeCategoryDTO,
                                   Principal principal) {

        switch (status) {
//            case "list":
//                return storeService.getCategoryList(storeCategoryDTO);
            case "insert":
                return storeService.saveCategory(storeCategoryDTO, principal);
            case "update":
                return storeService.updateCategory(storeCategoryDTO, principal);
            case "delete":
                return storeService.deleteCategory(storeCategoryDTO, principal);
        }

        throw new ApiException(ExceptionEnum.INVALID_PARAMS);
    }

    @PostMapping("/admin/menu")
    public ApiResponseDTO menu(@RequestParam String status,
                               @RequestPart(value = "menu") @Valid StoreMenuDTO storeMenuDTO,
                               @RequestPart(value = "img") MultipartFile imgFile,
                               Principal principal) throws IOException {
        switch (status) {
            case "insert":
                return storeService.saveMenu(storeMenuDTO, imgFile, principal);
            case "update":
            case "delete":
        }

        throw new ApiException(ExceptionEnum.INVALID_PARAMS);
    }

}
