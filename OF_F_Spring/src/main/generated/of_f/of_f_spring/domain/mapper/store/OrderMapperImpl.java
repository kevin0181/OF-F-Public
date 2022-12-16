package of_f.of_f_spring.domain.mapper.store;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
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

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-15T19:18:49+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_292 (AdoptOpenJDK)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public StoreOrder storeOrderDTOToStoreOrder(StoreOrderDTO storeOrderDTO) {
        if ( storeOrderDTO == null ) {
            return null;
        }

        StoreOrder storeOrder = new StoreOrder();

        storeOrder.setSeq( storeOrderDTO.getSeq() );
        storeOrder.setStoreSeq( storeOrderDTO.getStoreSeq() );
        storeOrder.setStoreQRIdSeq( storeOrderDTO.getStoreQRIdSeq() );
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

    @Override
    public StoreOrderDTO storeOrderToStoreOrderDTO(StoreOrder storeOrder) {
        if ( storeOrder == null ) {
            return null;
        }

        StoreOrderDTO.StoreOrderDTOBuilder storeOrderDTO = StoreOrderDTO.builder();

        storeOrderDTO.seq( storeOrder.getSeq() );
        storeOrderDTO.storeSeq( storeOrder.getStoreSeq() );
        storeOrderDTO.storeQRIdSeq( storeOrder.getStoreQRIdSeq() );
        storeOrderDTO.id( storeOrder.getId() );
        storeOrderDTO.kind( storeOrder.getKind() );
        storeOrderDTO.orderNumber( storeOrder.getOrderNumber() );
        storeOrderDTO.totalPrice( storeOrder.getTotalPrice() );
        storeOrderDTO.cancelAfterPrice( storeOrder.getCancelAfterPrice() );
        storeOrderDTO.date( storeOrder.getDate() );
        storeOrderDTO.place( storeOrder.getPlace() );
        storeOrderDTO.status( storeOrder.getStatus() );
        storeOrderDTO.payStatus( storeOrder.getPayStatus() );
        storeOrderDTO.phoneNumber( storeOrder.getPhoneNumber() );
        storeOrderDTO.email( storeOrder.getEmail() );
        storeOrderDTO.emailReceiveStatus( storeOrder.getEmailReceiveStatus() );
        storeOrderDTO.phoneNumberReceiveStatus( storeOrder.getPhoneNumberReceiveStatus() );
        storeOrderDTO.storeOrderPgInfo( storeOrderPgInfoToStoreOrderPgInfoDTO( storeOrder.getStoreOrderPgInfo() ) );
        storeOrderDTO.storeOrderVanInfo( storeOrderVanInfoToStoreOrderVanInfoDTO( storeOrder.getStoreOrderVanInfo() ) );
        storeOrderDTO.storeOrderMenus( storeOrderMenuListToStoreOrderMenuDTOList( storeOrder.getStoreOrderMenus() ) );

        return storeOrderDTO.build();
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

    protected StoreSideMenu storeSideMenuDTOToStoreSideMenu(StoreSideMenuDTO storeSideMenuDTO) {
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

    protected StoreSideCategory storeSideCategoryDTOToStoreSideCategory(StoreSideCategoryDTO storeSideCategoryDTO) {
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

    protected StoreMS storeMSDTOToStoreMS(StoreMSDTO storeMSDTO) {
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

    protected List<StoreMS> storeMSDTOListToStoreMSList(List<StoreMSDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreMS> list1 = new ArrayList<StoreMS>( list.size() );
        for ( StoreMSDTO storeMSDTO : list ) {
            list1.add( storeMSDTOToStoreMS( storeMSDTO ) );
        }

        return list1;
    }

    protected StoreMenu storeMenuDTOToStoreMenu(StoreMenuDTO storeMenuDTO) {
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
        storeMenu.storeMSs( storeMSDTOListToStoreMSList( storeMenuDTO.getStoreMSs() ) );

        return storeMenu.build();
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
        storeOrderMenu.setStoreMenu( storeMenuDTOToStoreMenu( storeOrderMenuDTO.getStoreMenu() ) );

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

    protected StoreSideMenuDTO storeSideMenuToStoreSideMenuDTO(StoreSideMenu storeSideMenu) {
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

    protected StoreSideCategoryDTO storeSideCategoryToStoreSideCategoryDTO(StoreSideCategory storeSideCategory) {
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

    protected StoreMenuDTO storeMenuToStoreMenuDTO(StoreMenu storeMenu) {
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
}
