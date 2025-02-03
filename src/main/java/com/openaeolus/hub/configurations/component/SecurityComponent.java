package com.openaeolus.hub.configurations.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openaeolus.hub.services.TokenService;
import com.openaeolus.hub.utils.annotations.Unsecured;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class SecurityComponent implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityComponent.class);

    private final TokenService tokenService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SecurityComponent(TokenService tokenService,ObjectMapper objectMapper) {
        this.tokenService = tokenService;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (handler instanceof HandlerMethod handlerMethod
                && (handlerMethod.getBeanType().isAnnotationPresent(Unsecured.class)
                || handlerMethod.getMethod().isAnnotationPresent(Unsecured.class))) {
                return true;
        }

        Optional<Cookie> cookieOptional = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("token")).findFirst();

        if(cookieOptional.isPresent()) {
            Cookie cookie = cookieOptional.get();
            if (tokenService.validateToken(cookie.getValue())) {
                request.setAttribute("token", cookie.getValue());
                return true;
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(parseMessage("Login then try again"));
        return false;
    }

    private String parseMessage(String message) throws JsonProcessingException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        String exceptionMessage = objectMapper.writeValueAsString(body);
        LOGGER.debug(exceptionMessage);
        return exceptionMessage;
    }
}
