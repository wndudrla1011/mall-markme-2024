package com.rootable.mallmarkme2024.jwt;

import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;

    //Jwt 토큰의 인증 정보를 현재 스레드의 SecurityContext 에 저장
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        //Request Header -> 토큰 추출
        String jwt = resolveToken(httpServletRequest);

        String requestURI = httpServletRequest.getRequestURI(); //요청 직전 URI 정보

        /*
        * 토큰 유효성 검사
        * 정상 토큰 -> 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
        * */
        String result = tokenProvider.validateToken(jwt);

        if (StringUtils.hasText(jwt) && result.equals("success")) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("SecurityContext 에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);

            Gson gson = new Gson();
            String value = "";

            switch (result) {
                case "malformed" -> value = "잘못된 JWT 서명입니다.";
                case "expired" -> value = "만료된 JWT 토큰입니다.";
                case "unsupported" -> value = "지원되지 않는 JWT 토큰입니다.";
                case "invalid" -> value = "JWT 토큰이 잘못되었습니다.";
            }

            String msg = gson.toJson(Map.of(result, value));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }

        filterChain.doFilter(request, response);

    }

    //Request Header -> 토큰 추출
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7); //토큰 값 추출 "Bearer {Token}"
        }

        return null;

    }

}
