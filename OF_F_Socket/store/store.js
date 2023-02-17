const {Router} = require("express");
const router = Router();
const {StoreMongo} = require("./../models");

router.post("/status", async (req, res, next) => {
    let {storeSeq} = req.query;

    let data = await StoreMongo.findOne({
        storeSeq: storeSeq
    });

    return res.json(data);
});

module.exports = router;