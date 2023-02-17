const {Router} = require("express");
const router = Router();
const {StoreMongo} = require("./../models");

router.post("/status", async (req, res, next) => {
    let data = await StoreMongo.find();
    return res.json(data);
});

module.exports = router;