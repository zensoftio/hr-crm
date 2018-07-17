var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const Nexmo = require('nexmo');
const nexmo = new Nexmo({
    apiKey: '11bafd6d',
    apiSecret: 'gbI3hMGS3hemoe9N'
});
module.exports = {
    send: function (json, title) {
        return __awaiter(this, void 0, void 0, function* () {
            let results = [];
            if (title === 'delete') {
                json.description = 'We decline interview!';
                json.location = '';
            }
            for (let i = 0; i < json.phone.length; i++) {
                let data = `Dear ${json.email[i]}, ${json.description}. Place: ${json.location}. Time: ${json.begin_time}`;
                const result = yield run(json.phone[i], data);
                (result) ? results.push(result) : result;
            }
            (results.length == json.phone.length) ? true : false;
        });
    }
};
function run(phone, data) {
    return __awaiter(this, void 0, void 0, function* () {
        return new Promise((resolve, reject) => {
            nexmo.message.sendSms('NEXMO', phone, data, (err, responseData) => {
                if (err) {
                    reject(false);
                }
                else {
                    resolve(true);
                }
            });
        });
    });
}
//# sourceMappingURL=sms.js.map