import {Outlet, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {useRecoilState} from "recoil";
import {
    orderStatus as orderStatusRecoil,
    orderStore as orderStoreRecoil,
    orderStoreCategory as orderStoreCategoryRecoil,
    orderStoreSideCategory as orderStoreSideCategoryRecoil
} from "../../store/order/orderStore";
import {nodeServerAxios, notTokenAxios} from "../../Config/customAxios";
import axios from "axios";

let OrderStoreCheck = () => {

    let navigate = useNavigate();
    let {storeId, qrId} = useParams();

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    const [orderStoreCategory, setOrderStoreCategory] = useRecoilState(orderStoreCategoryRecoil); // 가게 정보 카테고리
    const [orderStoreSideCategory, setOrderStoreSideCategory] = useRecoilState(orderStoreSideCategoryRecoil); // 가게 정보 사이드 카테고리
    const [orderStatus, setOrderStatus] = useRecoilState(orderStatusRecoil); // 가게 정보 사이드 카테고리

    useEffect(() => {

        if (storeId === null) {
            alert("존재하지 않는 가게 입니다.");
            navigate("/store/search");
        }

        if (isNaN(Number(storeId))) {
            alert("존재하지 않는 가게 정보입니다.");
            navigate("/store/search");
        }

        if (qrId === null) {
            alert("존재하지 않는 QR 정보입니다.");
            navigate("/store/search");
        }

        notTokenAxios({
            method: "get",
            url: `/api/v1/store/order/qr?storeSeq=${storeId}&qrId=${qrId}`,
        }).then(res => {

            if (res.data.data === null) {
                alert("존재하지 않는 가게 정보입니다.");
                navigate("/store/search");
            } else {
                setOrderStore(res.data.data);
                setOrderStoreCategory(res.data.data.storeCategories);
                setOrderStoreSideCategory(res.data.data.storeSideCategories);
                navigate(`/store/${storeId}/${qrId}/main`);
            }

        }).catch(err => {
            console.log(err);
            alert("존재하지 않는 QR 및 가게입니다.");
            navigate("/store/search");
        });

        nodeServerAxios({
            method: "POST",
            url: "/store/status?storeSeq=" + storeId
        }).then(res => {
            if (!res.data.status) {
                alert("가게 주문이 종료된 상태입니다. 가게에게 문의해주세요.");
            }
            setOrderStatus(res.data);
        }).catch(err => {
            alert("가게 오류입니다. 재접속해주세요.");
            navigate(-1);
        });

    }, []);
    return (
        <Outlet/>
    )
}

export default OrderStoreCheck;