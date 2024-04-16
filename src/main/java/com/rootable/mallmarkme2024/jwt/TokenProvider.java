package com.rootable.mallmarkme2024.jwt;

import com.rootable.mallmarkme2024.exception.CustomJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.*;

@Log4j2
@Component
public class TokenProvider {

    private static Key key;

    /*
    * 토큰 생성용 비밀키
    * */
    public TokenProvider(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    /*
    * 토큰 생성
    * */
    public String createToken(Map<String, Object> valueMap, int min) {

        return Jwts.builder()
                .setHeader(Map.of("typ", "JWT"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant())) //유효시간
                .signWith(key) //암호화 알고리즘
                .compact();

    }

    /*
    * 토큰 검증
    * */
    public static Map<String, Object> validateToken(String token) {

        Map<String, Object> claims = null;

        try {
            //토큰 복호화
            claims = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token) //파싱 및 검증, 실패 시 에러
                    .getBody();
        } catch (MalformedJwtException e) { //올바르지 않은 토큰값
            throw new CustomJwtException("Malformed");
        } catch (ExpiredJwtException e) { //토큰 만료
            throw new CustomJwtException("Expired");
        } catch (InvalidClaimException e) { //올바르지 않은 Claim 필드값
            throw new CustomJwtException("Invalid");
        } catch (JwtException e) { //그 외 JWT 관련 예외
            throw new CustomJwtException("JwtError");
        } catch (Exception e) { //그 외 예외
            throw new CustomJwtException("Error");
        }

        return claims;

    }

}
