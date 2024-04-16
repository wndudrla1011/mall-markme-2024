package com.rootable.mallmarkme2024.jwt;

import io.jsonwebtoken.security.SecurityException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private final long tokenValidityInMilliseconds;
    private final Key key;

    //토큰 생성에 사용될 비밀키 생성
    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000;
    }

    //토큰 생성
    public String createToken(Authentication authentication) {

        //권한들 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //현재 시간으로부터 유효시간 생성
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName()) //토큰이름
                .claim(AUTHORITIES_KEY, authorities) //권한 -> claim
                .signWith(key, SignatureAlgorithm.HS512) //암호화 알고리즘
                .setExpiration(validity) //유효시간
                .compact();

    }

    //Authentication 생성
    public Authentication getAuthentication(String token) {

        //토큰 복호화
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        //클레임으로부터 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(
                claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //UserDetails 객체를 만들어서 Authentication 리턴
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);

    }

    //토큰 유효성 검증
    public String validateToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return "success";
        } catch (SecurityException | MalformedJwtException e) {
            return "malformed";
        } catch (ExpiredJwtException e) {
            return "expired";
        } catch (UnsupportedJwtException e) {
            return "unsupported";
        } catch (IllegalArgumentException e) {
            return "invalid";
        }

    }

}
