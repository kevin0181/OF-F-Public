package of_f.of_f_spring.domain.mapper.store;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.menu.*;
import of_f.of_f_spring.domain.entity.store.qr.QRStoreInfo;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.menu.*;
import of_f.of_f_spring.dto.store.qr.QRStoreInfoDTO;
import of_f.of_f_spring.dto.store.qr.StoreQRIdDTO;
import of_f.of_f_spring.dto.total.StoreName;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface StoreMapper {
    StoreMapper instance = Mappers.getMapper(StoreMapper.class);

    Store storeDTOTOStore(StoreDTO storeDTO);


    StoreDTO storeToStoreDTO(Store store);

    @Mapping(target = "accountBankName", ignore = true)
    @Mapping(target = "accountNumber", ignore = true)
    @Mapping(target = "depositDate", ignore = true)
    @Mapping(target = "openDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "qrStoreInfo", ignore = true)
    @Mapping(target = "storePgSettings", ignore = true)
    @Mapping(target = "storeVanSettings", ignore = true)
    @Mapping(target = "storeOrders", ignore = true)
    @Mapping(target = "storeQRIds", ignore = true)
    StoreDTO storeToStoreDTOByOrderUser(Store store);

    StoreCategory storeCategoryDTOToStoreCategory(StoreCategoryDTO storeCategoryDTO);

    StoreCategoryDTO storeCategoryToStoreCategoryDTO(StoreCategory storeCategory);

    StoreMenu storeMenuToStoreMenuDTO(StoreMenuDTO storeMenuDTO);

    List<StoreMS> storeMSDTOToStoreMS(List<StoreMSDTO> storeMSDTOS);

    StoreMenuDTO storeMenuToStoreMenuDTO(StoreMenu storeMenu);

    QRStoreInfoDTO qrStoreInfoToQRStoreInfoDTO(QRStoreInfo qrStoreInfo);

    StoreSideCategory storeSideCategoryDTOToStoreSideCategory(StoreSideCategoryDTO storeSideCategoryDTO);

    StoreSideCategoryDTO storeSideCategoryToStoreSideCategoryDTO(StoreSideCategory storeSideCategory);

    StoreSideMenu storeSideMenuDTOToStoreSideMenu(StoreSideMenuDTO storeSideMenuDTO);

    StoreSideMenuDTO storeSideMenuToStoreSideMenuDTO(StoreSideMenu storeSideMenu);

    List<StoreMenuImg> storeMenuImgDTOToStoreMenuImg(List<StoreMenuImgDTO> storeMenuImgDTO);

    StoreMenuImg storeMenuImgDTOToStoreMenuImg2(StoreMenuImgDTO storeMenuImgDTO);

    List<StoreMenuImgDTO> storeMenuImgToStoreMenuImgDTO(List<StoreMenuImg> storeMenuImg);

    StoreSideImg storeSideImgDTOToStoreSideImg(StoreSideImgDTO storeSideImgDTOS);

    List<StoreSideImg> storeSideImgsDTOToStoreSideImgs(List<StoreSideImgDTO> storeSideImgDTOS);

    List<StoreName> storeToStoreName(List<Store> stores);
}
