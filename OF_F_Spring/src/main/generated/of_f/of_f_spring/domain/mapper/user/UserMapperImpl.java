package of_f.of_f_spring.domain.mapper.user;

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
import of_f.of_f_spring.domain.entity.user.User;
import of_f.of_f_spring.domain.entity.user.UserRole;
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
import of_f.of_f_spring.dto.user.ResUserDTO;
import of_f.of_f_spring.dto.user.UserDTO;
import of_f.of_f_spring.dto.user.UserRoleDTO;
import of_f.of_f_spring.dto.user.UserSignInDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-12T20:46:05+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_292 (AdoptOpenJDK)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User UserSignInDTOTOUser(UserSignInDTO userSignInDTO) {
        if ( userSignInDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.seq( userSignInDTO.getSeq() );
        user.email( userSignInDTO.getEmail() );
        user.password( userSignInDTO.getPassword() );
        user.name( userSignInDTO.getName() );
        user.phoneNumber( userSignInDTO.getPhoneNumber() );
        user.userStatus( userSignInDTO.getUserStatus() );
        user.emailReceiveStatus( userSignInDTO.isEmailReceiveStatus() );
        user.phoneNumberReceiveStatus( userSignInDTO.isPhoneNumberReceiveStatus() );
        user.userRoles( userRoleDTOListToUserRoleList( userSignInDTO.getUserRoles() ) );

        return user.build();
    }

    @Override
    public ResUserDTO userTOResUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        ResUserDTO.ResUserDTOBuilder resUserDTO = ResUserDTO.builder();

        resUserDTO.seq( user.getSeq() );
        resUserDTO.email( user.getEmail() );
        resUserDTO.name( user.getName() );
        resUserDTO.phoneNumber( user.getPhoneNumber() );
        resUserDTO.userStatus( user.getUserStatus() );
        resUserDTO.emailReceiveStatus( user.isEmailReceiveStatus() );
        resUserDTO.phoneNumberReceiveStatus( user.isPhoneNumberReceiveStatus() );
        resUserDTO.createTime( user.getCreateTime() );
        resUserDTO.userRoles( userRoleListToUserRoleDTOList( user.getUserRoles() ) );

        return resUserDTO.build();
    }

    @Override
    public UserDTO userTOUserDTO_N_ROLE(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.seq( user.getSeq() );
        userDTO.email( user.getEmail() );
        userDTO.name( user.getName() );
        userDTO.phoneNumber( user.getPhoneNumber() );
        userDTO.userStatus( user.getUserStatus() );
        userDTO.emailReceiveStatus( user.isEmailReceiveStatus() );
        userDTO.phoneNumberReceiveStatus( user.isPhoneNumberReceiveStatus() );
        userDTO.createTime( user.getCreateTime() );
        userDTO.stores( storeListToStoreDTOList( user.getStores() ) );

        return userDTO.build();
    }

    protected UserRole userRoleDTOToUserRole(UserRoleDTO userRoleDTO) {
        if ( userRoleDTO == null ) {
            return null;
        }

        UserRole.UserRoleBuilder userRole = UserRole.builder();

        userRole.seq( userRoleDTO.getSeq() );
        userRole.userSeq( userRoleDTO.getUserSeq() );
        userRole.roleSeq( userRoleDTO.getRoleSeq() );
        userRole.roles( userRoleDTO.getRoles() );

        return userRole.build();
    }

    protected List<UserRole> userRoleDTOListToUserRoleList(List<UserRoleDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<UserRole> list1 = new ArrayList<UserRole>( list.size() );
        for ( UserRoleDTO userRoleDTO : list ) {
            list1.add( userRoleDTOToUserRole( userRoleDTO ) );
        }

        return list1;
    }

    protected UserRoleDTO userRoleToUserRoleDTO(UserRole userRole) {
        if ( userRole == null ) {
            return null;
        }

        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setSeq( userRole.getSeq() );
        userRoleDTO.setUserSeq( userRole.getUserSeq() );
        userRoleDTO.setRoleSeq( userRole.getRoleSeq() );
        userRoleDTO.setRoles( userRole.getRoles() );

        return userRoleDTO;
    }

    protected List<UserRoleDTO> userRoleListToUserRoleDTOList(List<UserRole> list) {
        if ( list == null ) {
            return null;
        }

        List<UserRoleDTO> list1 = new ArrayList<UserRoleDTO>( list.size() );
        for ( UserRole userRole : list ) {
            list1.add( userRoleToUserRoleDTO( userRole ) );
        }

        return list1;
    }

    protected QRStoreInfoDTO qRStoreInfoToQRStoreInfoDTO(QRStoreInfo qRStoreInfo) {
        if ( qRStoreInfo == null ) {
            return null;
        }

        QRStoreInfoDTO.QRStoreInfoDTOBuilder qRStoreInfoDTO = QRStoreInfoDTO.builder();

        qRStoreInfoDTO.seq( qRStoreInfo.getSeq() );
        qRStoreInfoDTO.storeSeq( qRStoreInfo.getStoreSeq() );
        qRStoreInfoDTO.qrSubscribeSeq( qRStoreInfo.getQrSubscribeSeq() );
        qRStoreInfoDTO.qrSize( qRStoreInfo.getQrSize() );
        qRStoreInfoDTO.qrPayMoney( qRStoreInfo.getQrPayMoney() );
        qRStoreInfoDTO.qrPayStatus( qRStoreInfo.isQrPayStatus() );
        qRStoreInfoDTO.qrPayDate( qRStoreInfo.getQrPayDate() );

        return qRStoreInfoDTO.build();
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

        StoreMSDTO storeMSDTO = new StoreMSDTO();

        storeMSDTO.setSeq( storeMS.getSeq() );
        storeMSDTO.setStoreMenuSeq( storeMS.getStoreMenuSeq() );
        storeMSDTO.setStoreSideCategorySeq( storeMS.getStoreSideCategorySeq() );
        storeMSDTO.setStoreSideCategory( storeSideCategoryToStoreSideCategoryDTO( storeMS.getStoreSideCategory() ) );

        return storeMSDTO;
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

    protected StoreCategoryDTO storeCategoryToStoreCategoryDTO(StoreCategory storeCategory) {
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

    protected StoreDTO storeToStoreDTO(Store store) {
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
        storeDTO.qrStoreInfo( qRStoreInfoToQRStoreInfoDTO( store.getQrStoreInfo() ) );
        storeDTO.storePgSettings( storePgSettingListToStorePgSettingDTOList( store.getStorePgSettings() ) );
        storeDTO.storeVanSettings( storeVanSettingListToStoreVanSettingDTOList( store.getStoreVanSettings() ) );
        storeDTO.storeCategories( storeCategoryListToStoreCategoryDTOList( store.getStoreCategories() ) );
        storeDTO.storeOrders( storeOrderListToStoreOrderDTOList( store.getStoreOrders() ) );
        storeDTO.storeQRIds( storeQRIdListToStoreQRIdDTOList( store.getStoreQRIds() ) );

        return storeDTO.build();
    }

    protected List<StoreDTO> storeListToStoreDTOList(List<Store> list) {
        if ( list == null ) {
            return null;
        }

        List<StoreDTO> list1 = new ArrayList<StoreDTO>( list.size() );
        for ( Store store : list ) {
            list1.add( storeToStoreDTO( store ) );
        }

        return list1;
    }
}
