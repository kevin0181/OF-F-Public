const {Schema} = require("mongoose");
const shortId = require("./types/short-id");

module.exports = new Schema({
    shortId,
    store: String,
    status: Boolean,
}, {
    timestamps: true
});