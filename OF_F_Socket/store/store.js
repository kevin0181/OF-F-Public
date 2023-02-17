const {Router} = require("express");
const router = Router();
const {StoreMongo} = require("./../models");

router.post("/status", async (req, res, next) => { //현재 가게 상태 가져오기
    let {storeSeq} = req.query;

    let data = await StoreMongo.findOne({
        storeSeq: storeSeq
    });

    return res.json(data);
});

router.post("/set/status", async (req, res, next) => { //가게 상태 설정
    let data = req.header("accessToken");
    let storeData = req.body;
    console.log(data);
    console.log(storeData);
})

module.exports = router;