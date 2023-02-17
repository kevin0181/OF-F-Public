const {Router} = require("express");
const router = Router();

router.post("/status", async (req, res, next) => {
    return res.json({
        k: "1"
    })
});

module.exports = router;