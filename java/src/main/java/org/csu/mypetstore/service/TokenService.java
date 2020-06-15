package org.csu.mypetstore.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenService {
    public static String getToken(String username, String password ) {
        String token="";
        token= JWT.create().withAudience(username)// 将 username保存到 token 里面
                .sign(Algorithm.HMAC256(password));// 以 password 作为 token 的密钥
        return token;
    }

}
