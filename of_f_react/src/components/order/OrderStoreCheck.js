import {Outlet, useNavigate, useParams} from "react-router-dom";
import {useEffect} from "react";
import {useRecoilState} from "recoil";
import {
    orderStore as orderStoreRecoil,
    orderStoreCategory as orderStoreCategoryRecoil,
    orderStoreSideCategory as orderStoreSideCategoryRecoil
} from "../../store/order/orderStore";
import {nodeServerAxios, notTokenAxios} from "../../Config/customAxios";

let OrderStoreCheck = () => {

    let navigate = useNavigate();
    let {storeId, qrId} = useParams();

    const [orderStore, setOrderStore] = useRecoilState(orderStoreRecoil); // 가게 정보
    const [orderStoreCategory, setOrderStoreCategory] = useRecoilState(orderStoreCategoryRecoil); // 가게 정보 카테고리
    const [orderStoreSideCategory, setOrderStoreSideCategory] = useRecoilState(orderStoreSideCategoryRecoil); // 가게 정보 사이드 카테고리


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
            url: '/store/status'
        }).then(res => {
            console.log(res)
        }).catch(err => {
            console.log(err);
        })

    }, []);
    return (
        <Outlet/>
    )
}

export default OrderStoreCheck;