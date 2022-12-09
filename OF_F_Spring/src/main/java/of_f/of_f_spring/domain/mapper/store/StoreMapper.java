package of_f.of_f_spring.domain.mapper.store;

import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.menu.StoreCategory;
import of_f.of_f_spring.domain.entity.store.menu.StoreMS;
import of_f.of_f_spring.domain.entity.store.menu.StoreMenu;
import of_f.of_f_spring.domain.entity.store.qr.QRStoreInfo;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.menu.StoreCategoryDTO;
import of_f.of_f_spring.dto.store.menu.StoreMSDTO;
import of_f.of_f_spring.dto.store.menu.StoreMenuDTO;
import of_f.of_f_spring.dto.store.qr.QRStoreInfoDTO;
import of_f.of_f_spring.dto.store.qr.StoreQRIdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface StoreMapper {
    StoreMapper instance = Mappers.getMapper(StoreMapper.class);

    Store storeDTOTOStore(StoreDTO storeDTO);

    StoreCategory storeCategoryDTOToStoreCategory(StoreCategoryDTO storeCategoryDTO);

    StoreCategoryDTO storeCategoryToStoreCategoryDTO(StoreCategory storeCategory);

    StoreMenu storeMenuToStoreMenuDTO(StoreMenuDTO storeMenuDTO);

    List<StoreMS> storeMSDTOToStoreMS(List<StoreMSDTO> storeMSDTOS);

    StoreMenuDTO storeMenuToStoreMenuDTO(StoreMenu storeMenu);

    StoreDTO storeToStoreDTO(Store store);

    List<StoreQRId> storeQRIdDTOToStoreQRId(List<StoreQRIdDTO> storeQRIdDTO);

    QRStoreInfo qrStoreInfoDTOToQRStoreInfo(QRStoreInfoDTO qrStoreInfoDTO);

    QRStoreInfoDTO qrStoreInfoToQRStoreInfoDTO(QRStoreInfo qrStoreInfo);

}
