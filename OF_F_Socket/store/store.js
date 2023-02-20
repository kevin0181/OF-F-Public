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
    let storeData = req.body;
    console.log(accessToken);
    console.log(storeData);
    let tokenStatus = await decode(accessToken);
    console.log(tokenStatus);
})

module.exports = router;