package of_f.of_f_spring.domain.mapper.store;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import of_f.of_f_spring.domain.entity.store.Store;
import of_f.of_f_spring.domain.entity.store.StorePgSetting;
import of_f.of_f_spring.domain.entity.store.StoreVanSetting;
import of_f.of_f_spring.domain.entity.store.menu.StoreCategory;
import of_f.of_f_spring.domain.entity.store.menu.StoreMS;
import of_f.of_f_spring.domain.entity.store.menu.StoreMenu;
import of_f.of_f_spring.domain.entity.store.menu.StoreMenuImg;
import of_f.of_f_spring.domain.entity.store.menu.StoreSideCategory;
import of_f.of_f_spring.domain.entity.store.menu.StoreSideImg;
import of_f.of_f_spring.domain.entity.store.menu.StoreSideMenu;
import of_f.of_f_spring.domain.entity.store.order.StoreOrder;
import of_f.of_f_spring.domain.entity.store.order.StoreOrderMenu;
import of_f.of_f_spring.domain.entity.store.order.StoreOrderPgInfo;
import of_f.of_f_spring.domain.entity.store.order.StoreOrderSide;
import of_f.of_f_spring.domain.entity.store.order.StoreOrderVanInfo;
import of_f.of_f_spring.domain.entity.store.qr.QRStoreInfo;
import of_f.of_f_spring.domain.entity.store.qr.StoreQRId;
import of_f.of_f_spring.dto.store.StoreDTO;
import of_f.of_f_spring.dto.store.StorePgSettingDTO;
import of_f.of_f_spring.dto.store.StoreVanSettingDTO;
import of_f.of_f_spring.dto.store.menu.StoreCategoryDTO;
import of_f.of_f_spring.dto.store.menu.StoreMSDTO;
import of_f.of_f_spring.dto.store.menu.StoreMenuDTO;
import of_f.of_f_spring.dto.store.menu.StoreMenuImgDTO;
import of_f.of_f_spring.dto.store.menu.StoreSideCategoryDTO;
import of_f.of_f_spring.dto.store.menu.StoreSideImgDTO;
import of_f.of_f_spring.dto.store.menu.StoreSideMenuDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderMenuDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderPgInfoDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderSideDTO;
import of_f.of_f_spring.dto.store.order.StoreOrderVanInfoDTO;
import of_f.of_f_spring.dto.store.qr.QRStoreInfoDTO;
import of_f.of_f_spring.dto.store.qr.StoreQRIdDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-14T16:23:06+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_292 (AdoptOpenJDK)"
)
public class StoreMapperImpl implements StoreMapper {

    @Override
    public Store storeDTOTOStore(StoreDTO storeDTO) {
        if ( storeDTO == null ) {
            return null;
        }

        Store store = new Store();

        store.setSeq( storeDTO.getSeq() );
        store.setUserSeq( storeDTO.getUserSeq() );
        store.setName( storeDTO.getName() );
        store.setBusinessNumber( storeDTO.getBusinessNumber() );
        store.setAddress( storeDTO.getAddress() );
        store.setDetailAddress( storeDTO.getDetailAddress() );
        store.setAccountBankName( storeDTO.getAccountBankName() );
        store.setAccountNumber( storeDTO.getAccountNumber() );
        store.setDepositDate( storeDTO.getDepositDate() );
        store.setBusinessPhoneNumber( storeDTO.getBusinessPhoneNumber() );
        store.setOpenDate( storeDTO.getOpenDate() );
        store.setStatus( storeDTO.getStatus() );
        store.setQrStoreInfo( qRStoreInfoDTOToQRStoreInfo( storeDTO.getQrStoreInfo() ) );
        store.setStoreOrders( storeOrderDTOListToStoreOrderList( storeDTO.getStoreOrders() ) );
        store.setStorePgSettings( storePgSettingDTOListToStorePgSettingList( storeDTO.getStorePgSettings() ) );
        store.setStoreVanSettings( storeVanSettingDTOListToStoreVanSettingList( storeDTO.getStoreVanSettings() ) );
        store.setStoreCategories( storeCategoryDTOListToStoreCategoryList( storeDTO.getStoreCategories() ) );
        store.setStoreQRIds( storeQRIdDTOListToStoreQRIdList( storeDTO.getStoreQRIds() ) );

        return store;
    }

    @Override
    public StoreDTO storeToStoreDTO(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDTO.StoreDTOBuilder storeDTO = StoreDTO.builder();

        storeDTO.seq( store.getSeq() );
        storeDTO.userSeq( store.getUserSeq() );
        storeDTO.name( store.getName() );
        storeDTO.businessNumber( store.getBusinessNumber() );
        storeDTO.address( store.getAddress() );
        storeDTO.detailAddress( store.getDetailAddress() );
        storeDTO.accountBankName( store.getAccountBankName() );
        storeDTO.accountNumber( store.getAccountNumber() );
        storeDTO.depositDate( store.getDepositDate() );
        storeDTO.businessPhoneNumber( store.getBusinessPhoneNumber() );
        storeDTO.openDate( store.getOpenDate() );
        storeDTO.status( store.getStatus() );
        storeDTO.qrStoreInfo( qrStoreInfoToQRStoreInfoDTO( store.getQrStoreInfo() ) );
        storeDTO.storePgSettings( storePgSettingListToStorePgSettingDTOList( store.getStorePgSettings() ) );
        storeDTO.storeVanSettings( storeVanSettingListToStoreVanSettingDTOList( store.getStoreVanSettings() ) );
        storeDTO.storeCategories( storeCategoryListToStoreCategoryDTOList( store.getStoreCategories() ) );
        storeDTO.storeOrders( storeOrderListToStoreOrderDTOList( store.getStoreOrders() ) );
        storeDTO.storeQRIds( storeQRIdListToStoreQRIdDTOList( store.getStoreQRIds() ) );

        return storeDTO.build();
    }

    @Override
    public StoreDTO storeToStoreDTOByOrderUser(Store store) {
        if ( store == null ) {
            return null;
        }

        StoreDTO.StoreDTOBuilder storeDTO = StoreDTO.builder();

        storeDTO.seq( store.getSeq() );
        storeDTO.userSeq( store.getUserSeq() );
        storeDTO.name( store.getName() );
        storeDTO.businessNumber( store.getBusinessNumber() );
        storeDTO.address( store.getAddress() );
        storeDTO.detailAddress( store.getDetailAddress() );
        storeDTO.businessPhoneNumber( store.getBusinessPhoneNumber() );
        storeDTO.storeCategories( storeCategoryListToStoreCategoryDTOList( store.getStoreCategories() ) );

        return storeDTO.build();
    }

    @Override
    public StoreCategory storeCategoryDTOToStoreCategory(StoreCategoryDTO storeCategoryDTO) {
        if ( storeCategoryDTO == null ) {
            return null;
        }

        StoreCategory storeCategory = new StoreCategory();

        storeCategory.setSeq( storeCategoryDTO.getSeq() );
        storeCategory.setStoreSeq( storeCategoryDTO.getStoreSeq() );
        storeCategory.setName( storeCategoryDTO.getName() );
        storeCategory.setStatus( storeCategoryDTO.isStatus() );
        storeCategory.setStoreMenus( storeMenuDTOListToStoreMenuList( storeCategoryDTO.getStoreMenus() ) );

        return storeCategory;
    }

    @Override
    public StoreCategoryDTO storeCategoryToStoreCategoryDTO(StoreCategory storeCategory) {
        if ( storeCategory == null ) {
            return null;
        }

        StoreCategoryDTO.StoreCategoryDTOBuilder storeCategoryDTO = StoreCategoryDTO.builder();

        storeCategoryDTO.seq( storeCategory.getSeq() );
        storeCategoryDTO.storeSeq( storeCategory.getStoreSeq() );
        storeCategoryDTO.name( storeCategory.getName() );
        storeCategoryDTO.status( storeCategory.isStatus() );
        storeCategoryDTO.storeMenus( storeMenuListToStoreMenuDTOList( storeCategory.getStoreMenus() ) );

        return storeCategoryDTO.build();
    }

    @Override
    public StoreMenu storeMenuToStoreMenuDTO(StoreMenuDTO storeMenuDTO) {
        if ( storeMenuDTO == null ) {
            return null;
        }

        StoreMenu.StoreMenuBuilder storeMenu = StoreMenu.builder();

        storeMenu.seq( storeMenuDTO.getSeq() );
        storeMenu.storeCategorySeq( storeMenuDTO.getStoreCategorySeq() );
        storeMenu.name( storeMenuDTO.getName() );
        storeMenu.price( storeMenuDTO.getPrice() );
        storeMenu.status( storeMenuDTO.isStatus() );
        storeMenu.soldOutStatus( storeMenuDTO.isSoldOutStatus() );
        storeMenu.storeMenuImgs( storeMenuImgDTOListToStoreMenuImgList( storeMenuDTO.getStoreMenuImgs() ) );
        storeMenu.storeMSs( storeMSDTOToStoreMS( storeMenuDTO.getStoreMSs() ) );

        return storeMenu.build();
    }

    @Override
    public List<StoreMS> storeMSDTOToStoreMS(List<StoreMSDTO> storeMSDTOS) {
        if ( storeMSDTOS == null ) {
            return null;
        }

        List<StoreMS> list = new ArrayList<StoreMS>( storeMSDTOS.size() );
        for ( StoreMSDTO storeMSDTO : storeMSDTOS ) {
            list.add( storeMSDTOToStoreMS1( storeMSDTO ) );
        }

        return list;
    }

    @Override
    public StoreMenuDTO storeMenuToStoreMenuDTO(StoreMenu storeMenu) {
        if ( storeMenu == null ) {
            return null;
        }

        StoreMenuDTO storeMenuDTO = new StoreMenuDTO();

        storeMenuDTO.setSeq( storeMenu.getSeq() );
        storeMenuDTO.setStoreCategorySeq( storeMenu.getStoreCategorySeq() );
        storeMenuDTO.setName( storeMenu.getName() );
        storeMenuDTO.setPrice( storeMenu.getPrice() );
        storeMenuDTO.setStatus( storeMenu.isStatus() );
        storeMenuDTO.setSoldOutStatus( storeMenu.isSoldOutStatus() );
        storeMenuDTO.setStoreMenuImgs( storeMenuImgListToStoreMenuImgDTOList( storeMenu.getStoreMenuImgs() ) );
        storeMenuDTO.setStoreMSs( storeMSListToStoreMSDTOList( storeMenu.getStoreMSs() ) );

        return storeMenuDTO;
    }

    @Override
    public QRStoreInfoDTO qrStoreInfoToQRStoreInfoDTO(QRStoreInfo qrStoreInfo) {
        if ( qrStoreInfo == null ) {
            return null;
        }

        QRStoreInfoDTO.QRStoreInfoDTOBuilder qRStoreInfoDTO = QRStoreInfoDTO.builder();

        qRStoreInfoDTO.seq( qrStoreInfo.getSeq() );
        qRStoreInfoDTO.storeSeq( qrStoreInfo.getStoreSeq() );
        qRStoreInfoDTO.qrSubscribeSeq( qrStoreInfo.getQrSubscribeSeq() );
        qRStoreInfoDTO.qrSize( qrStoreInfo.getQrSize() );
        qRStoreInfoDTO.qrPayMoney( qrStoreInfo.getQrPayMoney() );
        qRStoreInfoDTO.qrPayStatus( qrStoreInfo.isQrPayStatus() );
        qRStoreInfoDTO.qrPayDate( qrStoreInfo.getQrPayDate() );

        return qRStoreInfoDTO.build();
    }

    @Override
    public StoreSideCategory storeSideCategoryDTOToStoreSideCategory(StoreSideCategoryDTO storeSideCategoryDTO) {
        if ( storeSideCategoryDTO == null ) {
            return null;
        }

        StoreSideCategory storeSideCategory = new StoreSideCategory();

        storeSideCategory.setSeq( storeSideCategoryDTO.getSeq() );
        storeSideCategory.setStoreSeq( storeSideCategoryDTO.getStoreSeq() );
        storeSideCategory.setName( storeSideCategoryDTO.getName() );
        storeSideCategory.setStatus( storeSideCategoryDTO.isStatus() );
        storeSideCategory.setStoreSideMenus( storeSideMenuDTOListToStoreSideMenuList( storeSideCategoryDTO.getStoreSideMenus() ) );

        return storeSideCategory;
    }

    @Override
    public StoreSideCategoryDTO storeSideCategoryToStoreSideCategoryDTO(StoreSideCategory storeSideCategory) {
        if ( storeSideCategory == null ) {
            return null;
        }

        StoreSideCategoryDTO.StoreSideCategoryDTOBuilder storeSideCategoryDTO = StoreSideCategoryDTO.builder();

        storeSideCategoryDTO.seq( storeSideCategory.getSeq() );
        storeSideCategoryDTO.storeSeq( storeSideCategory.getStoreSeq() );
        storeSideCategoryDTO.name( storeSideCategory.getName() );
        storeSideCategoryDTO.status( storeSideCategory.isStatus() );
        storeSideCategoryDTO.storeSideMenus( storeSideMenuListToStoreSideMenuDTOList( storeSideCategory.getStoreSideMenus() ) );

        return storeSideCategoryDTO.build();
    }

    @Override
    public StoreSideMenu storeSideMenuDTOToStoreSideMenu(StoreSideMenuDTO storeSideMenuDTO) {
        if ( storeSideMenuDTO == null ) {
            return null;
        }

        StoreSideMenu.StoreSideMenuBuilder storeSideMenu = StoreSideMenu.builder();

        storeSideMenu.seq( storeSideMenuDTO.getSeq() );
        storeSideMenu.storeSideCategorySeq( storeSideMenuDTO.getStoreSideCategorySeq() );
        storeSideMenu.name( storeSideMenuDTO.getName() );
        storeSideMenu.price( storeSideMenuDTO.getPrice() );
        storeSideMenu.status( storeSideMenuDTO.isStatus() );
        storeSideMenu.soldOutStatus( storeSideMenuDTO.isSoldOutStatus() );
        storeSideMenu.storeSideImgs( storeSideImgDTOListToStoreSideImgList( storeSideMenuDTO.getStoreSideImgs() ) );

        return storeSideMenu.build();
    }

    @Override
    public StoreSideMenuDTO storeSideMenuToStoreSideMenuDTO(StoreSideMenu storeSideMenu) {
        if ( storeSideMenu == null ) {
            return null;
        }

        StoreSideMenuDTO storeSideMenuDTO = new StoreSideMenuDTO();

        storeSideMenuDTO.setSeq( storeSideMenu.getSeq() );
        storeSideMenuDTO.setStoreSideCategorySeq( storeSideMenu.getStoreSideCategorySeq() );
        storeSideMenuDTO.setName( storeSideMenu.getName() );
        storeSideMenuDTO.setPrice( storeSideMenu.getPrice() );
        storeSideMenuDTO.setStatus( storeSideMenu.isStatus() );
        storeSideMenuDTO.setSoldOutStatus( storeSideMenu.isSoldOutStatus() );
        storeSideMenuDTO.setStoreSideImgs( storeSideImgListToStoreSideImgDTOList( storeSideMenu.getStoreSideImgs() ) );

        return storeSideMenuDTO;
    }

    protected QRStoreInfo qRStoreInfoDTOToQRStoreInfo(QRStoreInfoDTO qRStoreInfoDTO) {
        if ( qRStoreInfoDTO == null ) {
            return null;
        }

        QRStoreInfo.QRStoreInfoBuilder qRStoreInfo = QRStoreInfo.builder();

        qRStoreInfo.seq( qRStoreInfoDTO.getSeq() );
        qRStoreInfo.storeSeq( qRStoreInfoDTO.getStoreSeq() );
        qRStoreInfo.qrSubscribeSeq( qRStoreInfoDTO.getQrSubscribeSeq() );
        qRStoreInfo.qrSize( qRStoreInfoDTO.getQrSize() );
        qRStoreInfo.qrPayMoney( qRStoreInfoDTO.getQrPayMoney() );
        qRStoreInfo.qrPayStatus( qRStoreInfoDTO.isQrPayStatus() );
        qRStoreInfo.qrPayDate( qRStoreInfoDTO.getQrPayDate() );

        return qRStoreInfo.build();
    }

    protected StoreOrderSide storeOrderSideDTOToStoreOrderSide(StoreOrderSideDTO storeOrderSideDTO) {
        if ( storeOrderSideDTO == null ) {
            return null;
        }

        StoreOrderSide storeOrderSide = new StoreOrderSide();

        storeOrderSide.setSeq( storeOrderSideDTO.getSeq() );
        storeOrderSide.setStoreSideMenuSeq( storeOrderSideDTO.getStoreSideMenuSeq() );
        storeOrderSide.setStoreOrderMenuSeq( storeOrderSideDTO.getStoreOrderMenuSeq() );
        storeOrderSide.setSize( storeOrderSideDTO.getSize() );
        storeOrderSide.setStoreSideMenu( storeSideMenuDTOToStoreSideMenu( storeOrderSideDTO.getStoreSideMenu() ) );

        return storeOrderSide;
    }

    protected List<StoreOrderSide> storeOrderSideDTOListToStoreOrderSideList(List<StoreOrderSideDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreOrderSide> list1 = new ArrayList<StoreOrderSide>( list.size() );
        for ( StoreOrderSideDTO storeOrderSideDTO : list ) {
            list1.add( storeOrderSideDTOToStoreOrderSide( storeOrderSideDTO ) );
        }

        return list1;
    }

    protected StoreOrderMenu storeOrderMenuDTOToStoreOrderMenu(StoreOrderMenuDTO storeOrderMenuDTO) {
        if ( storeOrderMenuDTO == null ) {
            return null;
        }

        StoreOrderMenu storeOrderMenu = new StoreOrderMenu();

        storeOrderMenu.setSeq( storeOrderMenuDTO.getSeq() );
        storeOrderMenu.setStoreMenuSeq( storeOrderMenuDTO.getStoreMenuSeq() );
        storeOrderMenu.setStoreOrderSeq( storeOrderMenuDTO.getStoreOrderSeq() );
        storeOrderMenu.setSize( storeOrderMenuDTO.getSize() );
        storeOrderMenu.setStoreOrderSides( storeOrderSideDTOListToStoreOrderSideList( storeOrderMenuDTO.getStoreOrderSides() ) );
        storeOrderMenu.setStoreMenu( storeMenuToStoreMenuDTO( storeOrderMenuDTO.getStoreMenu() ) );

        return storeOrderMenu;
    }

    protected List<StoreOrderMenu> storeOrderMenuDTOListToStoreOrderMenuList(List<StoreOrderMenuDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreOrderMenu> list1 = new ArrayList<StoreOrderMenu>( list.size() );
        for ( StoreOrderMenuDTO storeOrderMenuDTO : list ) {
            list1.add( storeOrderMenuDTOToStoreOrderMenu( storeOrderMenuDTO ) );
        }

        return list1;
    }

    protected StoreOrderPgInfo storeOrderPgInfoDTOToStoreOrderPgInfo(StoreOrderPgInfoDTO storeOrderPgInfoDTO) {
        if ( storeOrderPgInfoDTO == null ) {
            return null;
        }

        StoreOrderPgInfo storeOrderPgInfo = new StoreOrderPgInfo();

        storeOrderPgInfo.setSeq( storeOrderPgInfoDTO.getSeq() );
        storeOrderPgInfo.setStoreOrderSeq( storeOrderPgInfoDTO.getStoreOrderSeq() );
        storeOrderPgInfo.setImpUid( storeOrderPgInfoDTO.getImpUid() );
        storeOrderPgInfo.setMerchantUid( storeOrderPgInfoDTO.getMerchantUid() );

        return storeOrderPgInfo;
    }

    protected StoreOrderVanInfo storeOrderVanInfoDTOToStoreOrderVanInfo(StoreOrderVanInfoDTO storeOrderVanInfoDTO) {
        if ( storeOrderVanInfoDTO == null ) {
            return null;
        }

        StoreOrderVanInfo storeOrderVanInfo = new StoreOrderVanInfo();

        storeOrderVanInfo.setSeq( storeOrderVanInfoDTO.getSeq() );
        storeOrderVanInfo.setStoreOrderSeq( storeOrderVanInfoDTO.getStoreOrderSeq() );
        storeOrderVanInfo.setTelegramNo( storeOrderVanInfoDTO.getTelegramNo() );
        storeOrderVanInfo.setTradeTime( storeOrderVanInfoDTO.getTradeTime() );
        storeOrderVanInfo.setApprovalNo( storeOrderVanInfoDTO.getApprovalNo() );
        storeOrderVanInfo.setTradeUniqueNo( storeOrderVanInfoDTO.getTradeUniqueNo() );
        storeOrderVanInfo.setCardKind( storeOrderVanInfoDTO.getCardKind() );
        storeOrderVanInfo.setCardNumber( storeOrderVanInfoDTO.getCardNumber() );
        storeOrderVanInfo.setDate( storeOrderVanInfoDTO.getDate() );

        return storeOrderVanInfo;
    }

    protected StoreOrder storeOrderDTOToStoreOrder(StoreOrderDTO storeOrderDTO) {
        if ( storeOrderDTO == null ) {
            return null;
        }

        StoreOrder storeOrder = new StoreOrder();

        storeOrder.setSeq( storeOrderDTO.getSeq() );
        storeOrder.setStoreSeq( storeOrderDTO.getStoreSeq() );
        storeOrder.setId( storeOrderDTO.getId() );
        storeOrder.setKind( storeOrderDTO.getKind() );
        storeOrder.setOrderNumber( storeOrderDTO.getOrderNumber() );
        storeOrder.setTotalPrice( storeOrderDTO.getTotalPrice() );
        storeOrder.setCancelAfterPrice( storeOrderDTO.getCancelAfterPrice() );
        storeOrder.setDate( storeOrderDTO.getDate() );
        if ( storeOrderDTO.getPlace() != null ) {
            storeOrder.setPlace( storeOrderDTO.getPlace() );
        }
        if ( storeOrderDTO.getStatus() != null ) {
            storeOrder.setStatus( storeOrderDTO.getStatus() );
        }
        if ( storeOrderDTO.getPayStatus() != null ) {
            storeOrder.setPayStatus( storeOrderDTO.getPayStatus() );
        }
        storeOrder.setPhoneNumber( storeOrderDTO.getPhoneNumber() );
        storeOrder.setEmail( storeOrderDTO.getEmail() );
        storeOrder.setEmailReceiveStatus( storeOrderDTO.getEmailReceiveStatus() );
        storeOrder.setPhoneNumberReceiveStatus( storeOrderDTO.getPhoneNumberReceiveStatus() );
        storeOrder.setStoreOrderMenus( storeOrderMenuDTOListToStoreOrderMenuList( storeOrderDTO.getStoreOrderMenus() ) );
        storeOrder.setStoreOrderPgInfo( storeOrderPgInfoDTOToStoreOrderPgInfo( storeOrderDTO.getStoreOrderPgInfo() ) );
        storeOrder.setStoreOrderVanInfo( storeOrderVanInfoDTOToStoreOrderVanInfo( storeOrderDTO.getStoreOrderVanInfo() ) );

        return storeOrder;
    }

    protected List<StoreOrder> storeOrderDTOListToStoreOrderList(List<StoreOrderDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreOrder> list1 = new ArrayList<StoreOrder>( list.size() );
        for ( StoreOrderDTO storeOrderDTO : list ) {
            list1.add( storeOrderDTOToStoreOrder( storeOrderDTO ) );
        }

        return list1;
    }

    protected StorePgSetting storePgSettingDTOToStorePgSetting(StorePgSettingDTO storePgSettingDTO) {
        if ( storePgSettingDTO == null ) {
            return null;
        }

        StorePgSetting storePgSetting = new StorePgSetting();

        storePgSetting.setSeq( storePgSettingDTO.getSeq() );
        storePgSetting.setStoreSeq( storePgSettingDTO.getStoreSeq() );
        storePgSetting.setName( storePgSettingDTO.getName() );
        storePgSetting.setValue( storePgSettingDTO.getValue() );

        return storePgSetting;
    }

    protected List<StorePgSetting> storePgSettingDTOListToStorePgSettingList(List<StorePgSettingDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StorePgSetting> list1 = new ArrayList<StorePgSetting>( list.size() );
        for ( StorePgSettingDTO storePgSettingDTO : list ) {
            list1.add( storePgSettingDTOToStorePgSetting( storePgSettingDTO ) );
        }

        return list1;
    }

    protected StoreVanSetting storeVanSettingDTOToStoreVanSetting(StoreVanSettingDTO storeVanSettingDTO) {
        if ( storeVanSettingDTO == null ) {
            return null;
        }

        StoreVanSetting storeVanSetting = new StoreVanSetting();

        storeVanSetting.setSeq( storeVanSettingDTO.getSeq() );
        storeVanSetting.setStoreSeq( storeVanSettingDTO.getStoreSeq() );
        storeVanSetting.setName( storeVanSettingDTO.getName() );
        storeVanSetting.setValue( storeVanSettingDTO.getValue() );

        return storeVanSetting;
    }

    protected List<StoreVanSetting> storeVanSettingDTOListToStoreVanSettingList(List<StoreVanSettingDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreVanSetting> list1 = new ArrayList<StoreVanSetting>( list.size() );
        for ( StoreVanSettingDTO storeVanSettingDTO : list ) {
            list1.add( storeVanSettingDTOToStoreVanSetting( storeVanSettingDTO ) );
        }

        return list1;
    }

    protected List<StoreCategory> storeCategoryDTOListToStoreCategoryList(List<StoreCategoryDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreCategory> list1 = new ArrayList<StoreCategory>( list.size() );
        for ( StoreCategoryDTO storeCategoryDTO : list ) {
            list1.add( storeCategoryDTOToStoreCategory( storeCategoryDTO ) );
        }

        return list1;
    }

    protected StoreQRId storeQRIdDTOToStoreQRId(StoreQRIdDTO storeQRIdDTO) {
        if ( storeQRIdDTO == null ) {
            return null;
        }

        StoreQRId.StoreQRIdBuilder storeQRId = StoreQRId.builder();

        storeQRId.seq( storeQRIdDTO.getSeq() );
        storeQRId.storeSeq( storeQRIdDTO.getStoreSeq() );
        storeQRId.id( storeQRIdDTO.getId() );

        return storeQRId.build();
    }

    protected List<StoreQRId> storeQRIdDTOListToStoreQRIdList(List<StoreQRIdDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreQRId> list1 = new ArrayList<StoreQRId>( list.size() );
        for ( StoreQRIdDTO storeQRIdDTO : list ) {
            list1.add( storeQRIdDTOToStoreQRId( storeQRIdDTO ) );
        }

        return list1;
    }

    protected StorePgSettingDTO storePgSettingToStorePgSettingDTO(StorePgSetting storePgSetting) {
        if ( storePgSetting == null ) {
            return null;
        }

        StorePgSettingDTO storePgSettingDTO = new StorePgSettingDTO();

        storePgSettingDTO.setSeq( storePgSetting.getSeq() );
        storePgSettingDTO.setStoreSeq( storePgSetting.getStoreSeq() );
        storePgSettingDTO.setName( storePgSetting.getName() );
        storePgSettingDTO.setValue( storePgSetting.getValue() );

        return storePgSettingDTO;
    }

    protected List<StorePgSettingDTO> storePgSettingListToStorePgSettingDTOList(List<StorePgSetting> list) {
        if ( list == null ) {
            return null;
        }

        List<StorePgSettingDTO> list1 = new ArrayList<StorePgSettingDTO>( list.size() );
        for ( StorePgSetting storePgSetting : list ) {
            list1.add( storePgSettingToStorePgSettingDTO( storePgSetting ) );
        }

        return list1;
    }

    protected StoreVanSettingDTO storeVanSettingToStoreVanSettingDTO(StoreVanSetting storeVanSetting) {
        if ( storeVanSetting == null ) {
            return null;
        }

        StoreVanSettingDTO storeVanSettingDTO = new StoreVanSettingDTO();

        storeVanSettingDTO.setSeq( storeVanSetting.getSeq() );
        storeVanSettingDTO.setStoreSeq( storeVanSetting.getStoreSeq() );
        storeVanSettingDTO.setName( storeVanSetting.getName() );
        storeVanSettingDTO.setValue( storeVanSetting.getValue() );

        return storeVanSettingDTO;
    }

    protected List<StoreVanSettingDTO> storeVanSettingListToStoreVanSettingDTOList(List<StoreVanSetting> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreVanSettingDTO> list1 = new ArrayList<StoreVanSettingDTO>( list.size() );
        for ( StoreVanSetting storeVanSetting : list ) {
            list1.add( storeVanSettingToStoreVanSettingDTO( storeVanSetting ) );
        }

        return list1;
    }

    protected List<StoreCategoryDTO> storeCategoryListToStoreCategoryDTOList(List<StoreCategory> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreCategoryDTO> list1 = new ArrayList<StoreCategoryDTO>( list.size() );
        for ( StoreCategory storeCategory : list ) {
            list1.add( storeCategoryToStoreCategoryDTO( storeCategory ) );
        }

        return list1;
    }

    protected StoreOrderPgInfoDTO storeOrderPgInfoToStoreOrderPgInfoDTO(StoreOrderPgInfo storeOrderPgInfo) {
        if ( storeOrderPgInfo == null ) {
            return null;
        }

        StoreOrderPgInfoDTO storeOrderPgInfoDTO = new StoreOrderPgInfoDTO();

        storeOrderPgInfoDTO.setSeq( storeOrderPgInfo.getSeq() );
        storeOrderPgInfoDTO.setStoreOrderSeq( storeOrderPgInfo.getStoreOrderSeq() );
        storeOrderPgInfoDTO.setImpUid( storeOrderPgInfo.getImpUid() );
        storeOrderPgInfoDTO.setMerchantUid( storeOrderPgInfo.getMerchantUid() );

        return storeOrderPgInfoDTO;
    }

    protected StoreOrderVanInfoDTO storeOrderVanInfoToStoreOrderVanInfoDTO(StoreOrderVanInfo storeOrderVanInfo) {
        if ( storeOrderVanInfo == null ) {
            return null;
        }

        StoreOrderVanInfoDTO storeOrderVanInfoDTO = new StoreOrderVanInfoDTO();

        storeOrderVanInfoDTO.setSeq( storeOrderVanInfo.getSeq() );
        storeOrderVanInfoDTO.setStoreOrderSeq( storeOrderVanInfo.getStoreOrderSeq() );
        storeOrderVanInfoDTO.setTelegramNo( storeOrderVanInfo.getTelegramNo() );
        storeOrderVanInfoDTO.setTradeTime( storeOrderVanInfo.getTradeTime() );
        storeOrderVanInfoDTO.setApprovalNo( storeOrderVanInfo.getApprovalNo() );
        storeOrderVanInfoDTO.setTradeUniqueNo( storeOrderVanInfo.getTradeUniqueNo() );
        storeOrderVanInfoDTO.setCardKind( storeOrderVanInfo.getCardKind() );
        storeOrderVanInfoDTO.setCardNumber( storeOrderVanInfo.getCardNumber() );
        storeOrderVanInfoDTO.setDate( storeOrderVanInfo.getDate() );

        return storeOrderVanInfoDTO;
    }

    protected StoreOrderSideDTO storeOrderSideToStoreOrderSideDTO(StoreOrderSide storeOrderSide) {
        if ( storeOrderSide == null ) {
            return null;
        }

        StoreOrderSideDTO storeOrderSideDTO = new StoreOrderSideDTO();

        storeOrderSideDTO.setSeq( storeOrderSide.getSeq() );
        storeOrderSideDTO.setStoreSideMenuSeq( storeOrderSide.getStoreSideMenuSeq() );
        storeOrderSideDTO.setStoreOrderMenuSeq( storeOrderSide.getStoreOrderMenuSeq() );
        storeOrderSideDTO.setSize( storeOrderSide.getSize() );
        storeOrderSideDTO.setStoreSideMenu( storeSideMenuToStoreSideMenuDTO( storeOrderSide.getStoreSideMenu() ) );

        return storeOrderSideDTO;
    }

    protected List<StoreOrderSideDTO> storeOrderSideListToStoreOrderSideDTOList(List<StoreOrderSide> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreOrderSideDTO> list1 = new ArrayList<StoreOrderSideDTO>( list.size() );
        for ( StoreOrderSide storeOrderSide : list ) {
            list1.add( storeOrderSideToStoreOrderSideDTO( storeOrderSide ) );
        }

        return list1;
    }

    protected StoreOrderMenuDTO storeOrderMenuToStoreOrderMenuDTO(StoreOrderMenu storeOrderMenu) {
        if ( storeOrderMenu == null ) {
            return null;
        }

        StoreOrderMenuDTO storeOrderMenuDTO = new StoreOrderMenuDTO();

        storeOrderMenuDTO.setSeq( storeOrderMenu.getSeq() );
        storeOrderMenuDTO.setStoreMenuSeq( storeOrderMenu.getStoreMenuSeq() );
        storeOrderMenuDTO.setStoreOrderSeq( storeOrderMenu.getStoreOrderSeq() );
        storeOrderMenuDTO.setSize( storeOrderMenu.getSize() );
        storeOrderMenuDTO.setStoreOrderSides( storeOrderSideListToStoreOrderSideDTOList( storeOrderMenu.getStoreOrderSides() ) );
        storeOrderMenuDTO.setStoreMenu( storeMenuToStoreMenuDTO( storeOrderMenu.getStoreMenu() ) );

        return storeOrderMenuDTO;
    }

    protected List<StoreOrderMenuDTO> storeOrderMenuListToStoreOrderMenuDTOList(List<StoreOrderMenu> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreOrderMenuDTO> list1 = new ArrayList<StoreOrderMenuDTO>( list.size() );
        for ( StoreOrderMenu storeOrderMenu : list ) {
            list1.add( storeOrderMenuToStoreOrderMenuDTO( storeOrderMenu ) );
        }

        return list1;
    }

    protected StoreOrderDTO storeOrderToStoreOrderDTO(StoreOrder storeOrder) {
        if ( storeOrder == null ) {
            return null;
        }

        StoreOrderDTO storeOrderDTO = new StoreOrderDTO();

        storeOrderDTO.setSeq( storeOrder.getSeq() );
        storeOrderDTO.setStoreSeq( storeOrder.getStoreSeq() );
        storeOrderDTO.setId( storeOrder.getId() );
        storeOrderDTO.setKind( storeOrder.getKind() );
        storeOrderDTO.setOrderNumber( storeOrder.getOrderNumber() );
        storeOrderDTO.setTotalPrice( storeOrder.getTotalPrice() );
        storeOrderDTO.setCancelAfterPrice( storeOrder.getCancelAfterPrice() );
        storeOrderDTO.setDate( storeOrder.getDate() );
        storeOrderDTO.setPlace( storeOrder.getPlace() );
        storeOrderDTO.setStatus( storeOrder.getStatus() );
        storeOrderDTO.setPayStatus( storeOrder.getPayStatus() );
        storeOrderDTO.setPhoneNumber( storeOrder.getPhoneNumber() );
        storeOrderDTO.setEmail( storeOrder.getEmail() );
        storeOrderDTO.setEmailReceiveStatus( storeOrder.getEmailReceiveStatus() );
        storeOrderDTO.setPhoneNumberReceiveStatus( storeOrder.getPhoneNumberReceiveStatus() );
        storeOrderDTO.setStoreOrderPgInfo( storeOrderPgInfoToStoreOrderPgInfoDTO( storeOrder.getStoreOrderPgInfo() ) );
        storeOrderDTO.setStoreOrderVanInfo( storeOrderVanInfoToStoreOrderVanInfoDTO( storeOrder.getStoreOrderVanInfo() ) );
        storeOrderDTO.setStoreOrderMenus( storeOrderMenuListToStoreOrderMenuDTOList( storeOrder.getStoreOrderMenus() ) );

        return storeOrderDTO;
    }

    protected List<StoreOrderDTO> storeOrderListToStoreOrderDTOList(List<StoreOrder> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreOrderDTO> list1 = new ArrayList<StoreOrderDTO>( list.size() );
        for ( StoreOrder storeOrder : list ) {
            list1.add( storeOrderToStoreOrderDTO( storeOrder ) );
        }

        return list1;
    }

    protected StoreQRIdDTO storeQRIdToStoreQRIdDTO(StoreQRId storeQRId) {
        if ( storeQRId == null ) {
            return null;
        }

        StoreQRIdDTO.StoreQRIdDTOBuilder storeQRIdDTO = StoreQRIdDTO.builder();

        storeQRIdDTO.seq( storeQRId.getSeq() );
        storeQRIdDTO.storeSeq( storeQRId.getStoreSeq() );
        storeQRIdDTO.id( storeQRId.getId() );

        return storeQRIdDTO.build();
    }

    protected List<StoreQRIdDTO> storeQRIdListToStoreQRIdDTOList(List<StoreQRId> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreQRIdDTO> list1 = new ArrayList<StoreQRIdDTO>( list.size() );
        for ( StoreQRId storeQRId : list ) {
            list1.add( storeQRIdToStoreQRIdDTO( storeQRId ) );
        }

        return list1;
    }

    protected List<StoreMenu> storeMenuDTOListToStoreMenuList(List<StoreMenuDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreMenu> list1 = new ArrayList<StoreMenu>( list.size() );
        for ( StoreMenuDTO storeMenuDTO : list ) {
            list1.add( storeMenuToStoreMenuDTO( storeMenuDTO ) );
        }

        return list1;
    }

    protected List<StoreMenuDTO> storeMenuListToStoreMenuDTOList(List<StoreMenu> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreMenuDTO> list1 = new ArrayList<StoreMenuDTO>( list.size() );
        for ( StoreMenu storeMenu : list ) {
            list1.add( storeMenuToStoreMenuDTO( storeMenu ) );
        }

        return list1;
    }

    protected StoreMenuImg storeMenuImgDTOToStoreMenuImg(StoreMenuImgDTO storeMenuImgDTO) {
        if ( storeMenuImgDTO == null ) {
            return null;
        }

        StoreMenuImg.StoreMenuImgBuilder storeMenuImg = StoreMenuImg.builder();

        storeMenuImg.seq( storeMenuImgDTO.getSeq() );
        storeMenuImg.storeMenuSeq( storeMenuImgDTO.getStoreMenuSeq() );
        storeMenuImg.name( storeMenuImgDTO.getName() );
        storeMenuImg.url( storeMenuImgDTO.getUrl() );
        storeMenuImg.extension( storeMenuImgDTO.getExtension() );
        storeMenuImg.date( storeMenuImgDTO.getDate() );

        return storeMenuImg.build();
    }

    protected List<StoreMenuImg> storeMenuImgDTOListToStoreMenuImgList(List<StoreMenuImgDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreMenuImg> list1 = new ArrayList<StoreMenuImg>( list.size() );
        for ( StoreMenuImgDTO storeMenuImgDTO : list ) {
            list1.add( storeMenuImgDTOToStoreMenuImg( storeMenuImgDTO ) );
        }

        return list1;
    }

    protected StoreMS storeMSDTOToStoreMS1(StoreMSDTO storeMSDTO) {
        if ( storeMSDTO == null ) {
            return null;
        }

        StoreMS storeMS = new StoreMS();

        storeMS.setSeq( storeMSDTO.getSeq() );
        storeMS.setStoreMenuSeq( storeMSDTO.getStoreMenuSeq() );
        storeMS.setStoreSideCategorySeq( storeMSDTO.getStoreSideCategorySeq() );
        storeMS.setStoreSideCategory( storeSideCategoryDTOToStoreSideCategory( storeMSDTO.getStoreSideCategory() ) );

        return storeMS;
    }

    protected StoreMenuImgDTO storeMenuImgToStoreMenuImgDTO(StoreMenuImg storeMenuImg) {
        if ( storeMenuImg == null ) {
            return null;
        }

        StoreMenuImgDTO storeMenuImgDTO = new StoreMenuImgDTO();

        storeMenuImgDTO.setSeq( storeMenuImg.getSeq() );
        storeMenuImgDTO.setStoreMenuSeq( storeMenuImg.getStoreMenuSeq() );
        storeMenuImgDTO.setName( storeMenuImg.getName() );
        storeMenuImgDTO.setUrl( storeMenuImg.getUrl() );
        storeMenuImgDTO.setExtension( storeMenuImg.getExtension() );
        storeMenuImgDTO.setDate( storeMenuImg.getDate() );

        return storeMenuImgDTO;
    }

    protected List<StoreMenuImgDTO> storeMenuImgListToStoreMenuImgDTOList(List<StoreMenuImg> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreMenuImgDTO> list1 = new ArrayList<StoreMenuImgDTO>( list.size() );
        for ( StoreMenuImg storeMenuImg : list ) {
            list1.add( storeMenuImgToStoreMenuImgDTO( storeMenuImg ) );
        }

        return list1;
    }

    protected StoreMSDTO storeMSToStoreMSDTO(StoreMS storeMS) {
        if ( storeMS == null ) {
            return null;
        }

        StoreMSDTO.StoreMSDTOBuilder storeMSDTO = StoreMSDTO.builder();

        storeMSDTO.seq( storeMS.getSeq() );
        storeMSDTO.storeMenuSeq( storeMS.getStoreMenuSeq() );
        storeMSDTO.storeSideCategorySeq( storeMS.getStoreSideCategorySeq() );
        storeMSDTO.storeSideCategory( storeSideCategoryToStoreSideCategoryDTO( storeMS.getStoreSideCategory() ) );

        return storeMSDTO.build();
    }

    protected List<StoreMSDTO> storeMSListToStoreMSDTOList(List<StoreMS> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreMSDTO> list1 = new ArrayList<StoreMSDTO>( list.size() );
        for ( StoreMS storeMS : list ) {
            list1.add( storeMSToStoreMSDTO( storeMS ) );
        }

        return list1;
    }

    protected List<StoreSideMenu> storeSideMenuDTOListToStoreSideMenuList(List<StoreSideMenuDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreSideMenu> list1 = new ArrayList<StoreSideMenu>( list.size() );
        for ( StoreSideMenuDTO storeSideMenuDTO : list ) {
            list1.add( storeSideMenuDTOToStoreSideMenu( storeSideMenuDTO ) );
        }

        return list1;
    }

    protected List<StoreSideMenuDTO> storeSideMenuListToStoreSideMenuDTOList(List<StoreSideMenu> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreSideMenuDTO> list1 = new ArrayList<StoreSideMenuDTO>( list.size() );
        for ( StoreSideMenu storeSideMenu : list ) {
            list1.add( storeSideMenuToStoreSideMenuDTO( storeSideMenu ) );
        }

        return list1;
    }

    protected StoreSideImg storeSideImgDTOToStoreSideImg(StoreSideImgDTO storeSideImgDTO) {
        if ( storeSideImgDTO == null ) {
            return null;
        }

        StoreSideImg.StoreSideImgBuilder storeSideImg = StoreSideImg.builder();

        storeSideImg.seq( storeSideImgDTO.getSeq() );
        storeSideImg.storeSideMenuSeq( storeSideImgDTO.getStoreSideMenuSeq() );
        storeSideImg.name( storeSideImgDTO.getName() );
        storeSideImg.url( storeSideImgDTO.getUrl() );
        storeSideImg.extension( storeSideImgDTO.getExtension() );
        storeSideImg.date( storeSideImgDTO.getDate() );

        return storeSideImg.build();
    }

    protected List<StoreSideImg> storeSideImgDTOListToStoreSideImgList(List<StoreSideImgDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreSideImg> list1 = new ArrayList<StoreSideImg>( list.size() );
        for ( StoreSideImgDTO storeSideImgDTO : list ) {
            list1.add( storeSideImgDTOToStoreSideImg( storeSideImgDTO ) );
        }

        return list1;
    }

    protected StoreSideImgDTO storeSideImgToStoreSideImgDTO(StoreSideImg storeSideImg) {
        if ( storeSideImg == null ) {
            return null;
        }

        StoreSideImgDTO storeSideImgDTO = new StoreSideImgDTO();

        storeSideImgDTO.setSeq( storeSideImg.getSeq() );
        storeSideImgDTO.setStoreSideMenuSeq( storeSideImg.getStoreSideMenuSeq() );
        storeSideImgDTO.setName( storeSideImg.getName() );
        storeSideImgDTO.setUrl( storeSideImg.getUrl() );
        storeSideImgDTO.setExtension( storeSideImg.getExtension() );
        storeSideImgDTO.setDate( storeSideImg.getDate() );

        return storeSideImgDTO;
    }

    protected List<StoreSideImgDTO> storeSideImgListToStoreSideImgDTOList(List<StoreSideImg> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreSideImgDTO> list1 = new ArrayList<StoreSideImgDTO>( list.size() );
        for ( StoreSideImg storeSideImg : list ) {
            list1.add( storeSideImgToStoreSideImgDTO( storeSideImg ) );
        }

        return list1;
    }
}
