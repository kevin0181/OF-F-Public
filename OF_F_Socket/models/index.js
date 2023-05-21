const mongoose = require("mongoose");
const StoreSchema = require("./schemas/store");

exports.StoreMongo = mongoose.model('store', StoreSchema);