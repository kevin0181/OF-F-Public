package of_f.of_f_spring.controller.store;

import of_f.of_f_spring.domain.exception.ApiException;
import of_f.of_f_spring.domain.exception.ExceptionEnum;
import of_f.of_f_spring.dto.response.ApiResponseDTO;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.menu.*;
import of_f.of_f_spring.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/app/req") // 가맹점 신청
    public ApiResponseDTO putAppNewStore(@RequestBody @Valid StoreDTO storeDTO, Principal principal) {
        return storeService.applicationNewStore(storeDTO, principal);
    }

    @GetMapping("/admin") // 가맹점 정보 (가맹점 관리자용)
    public ApiResponseDTO getStoreInfo(Principal principal) {
        return storeService.getStoreInfoAdmin(principal);
    }

    @PostMapping("/admin/category")
    public ApiResponseDTO category(@RequestParam String status,
                                   @RequestBody @Valid StoreCategoryDTO storeCategoryDTO,
                                   Principal principal) {

        switch (status) {
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
                               @RequestPart(value = "img", required = false) List<MultipartFile> imgFile,
                               Principal principal) {
        switch (status) {
            case "insert":
                return storeService.saveMenu(storeMenuDTO, imgFile, principal);
            case "update":
                return storeService.updateMenu(storeMenuDTO, principal); // 이미지 제외, 업데이트
            case "delete":
                return storeService.deleteMenu(storeMenuDTO, principal);
        }

        throw new ApiException(ExceptionEnum.INVALID_PARAMS);
    }

    @GetMapping("/admin/qr/code") //qr코드 저장
    public ApiResponseDTO qrCodeIdSave(@RequestParam String id,
                                       @RequestParam Long storeSeq,
                                       Principal principal) {
        return storeService.saveStoreQRId(id, storeSeq, principal);
    }

    @PostMapping("/admin/side/category")
    public ApiResponseDTO sideCategory(@RequestParam String status,
                                       @RequestBody @Valid StoreSideCategoryDTO storeSideCategoryDTO,
                                       Principal principal) {
        switch (status) {
            case "insert":
                return storeService.saveSideCategory(storeSideCategoryDTO, principal);
            case "update":
                return storeService.updateSideCategory(storeSideCategoryDTO, principal);
            case "delete":
                return storeService.deleteSideCategory(storeSideCategoryDTO, principal);
        }

        throw new ApiException(ExceptionEnum.INVALID_PARAMS);
    }

    @PostMapping("/admin/side/menu")
    public ApiResponseDTO sideMenu(@RequestParam String status,
                                   @RequestPart(value = "side-menu") @Valid StoreSideMenuDTO storeSideMenuDTO,
                                   @RequestPart(value = "side-img", required = false) List<MultipartFile> imgFile,
                                   Principal principal) {
        switch (status) {
            case "insert":
                return storeService.saveSideMenu(storeSideMenuDTO, imgFile, principal);
            case "update":
                return storeService.updateSideMenu(storeSideMenuDTO, principal);
            case "delete":
                return storeService.deleteSideMenu(storeSideMenuDTO, principal);
        }

        throw new ApiException(ExceptionEnum.INVALID_PARAMS);
    }


    @PostMapping("/admin/menu/side/connect")
    public ApiResponseDTO menuAndSideConnect(@RequestBody List<StoreMSDTO> storeMSDTOS) {
        return storeService.menuConnectSideService(storeMSDTOS);
    }
}
