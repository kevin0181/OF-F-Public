const {Schema} = require("mongoose");
const shortId = require("./types/short-id");

module.exports = new Schema({
    shortId,
    storeSeq: String,
    status: Boolean,
}, {
    timestamps: true
});