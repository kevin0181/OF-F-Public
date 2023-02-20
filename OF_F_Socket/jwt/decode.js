const jwt = require("jsonwebtoken");

exports.decode = async (accessTokenData) => {
    const jwtSecretKey = process.env.JWT_SECRET;

    const accessToken = accessTokenData.substring(7);

    //토큰 값이 비워져 있는지 체크합니다.
    if (accessToken === null || accessToken === undefined) {
        //만약 비워져 있다면 오류를 발생시킵니다.
        return false;
    } else { //비워져있지 않다면?

        try {
            // 비동기 처리로 jwt의 인증을 합니다.
            const tokenInfo = await new Promise((resolve, reject) => {
                jwt.verify(accessToken, jwtSecretKey, (err, decode) => {
                    if (err) {
                        reject(err); //인증 과정 중 오류가 나면 reject
                    } else {
                        resolve(decode); //인증 성공시 resolve
                    }
                });
            });

            //request의 tokenInfo부분에 토큰의 인증에 사용된 값을 저장합니다.
            req.tokenInfo = tokenInfo;

        } catch (e) {
            // res.status(403).json({status: false, message: "권한 오류"});
            console.log(e);
            return false;
        }
    }
}