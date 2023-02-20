const {Router} = require("express");
const router = Router();
const {StoreMongo} = require("./../models");
const {decode} = require("./../jwt/decode");

router.post("/status", async (req, res, next) => { //현재 가게 상태 가져오기
    let {storeSeq} = req.query;

    let data = await StoreMongo.findOne({
        storeSeq: storeSeq
    });

    return res.json(data);
});

router.post("/set/status", async (req, res, next) => { //가게 상태 설정

    let accessToken = req.header("accessToken");
    if (accessToken === null) {
        return res.json({
            error: "토큰이 존재하지 않습니다.",
            errorCode: "404",
            message: "존재하지 않는 토큰",
            detail: "로그인을 해주세요."
        });
    }

    let storeData = req.body;

    console.log(storeData);

    try {

        let data = await StoreMongo.findOne({
            storeSeq: storeData.storeSeq
        });

        console.log(data)

        if (data) {
            await StoreMongo.updateOne({
                storeSeq: data.storeSeq
            }, {
                storeSeq: data.storeSeq,
                status: !data.status
            });

            data = await StoreMongo.findOne({
                storeSeq: storeData.storeSeq
            });

        } else {
            data = await StoreMongo.create({
                storeSeq: storeData.storeSeq,
                status: !storeData.status
            });
        }


        return res.json({
            data: data,
            message: "저장 성공",
            detail: "주문을 시작했습니다."
        })
    } catch (e) {
        return res.json({
            error: "저장 실패",
            errorCode: "401",
            message: "서버 상태 저장 실패",
            detail: "주문을 시작하지 못했습니다. 관리자에게 문의 주세요."
        });
    }


})

module.exports = router;