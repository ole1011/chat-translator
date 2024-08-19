package de.ole101.translator.api.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import static java.util.Optional.ofNullable;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        String queryParameterString = ofNullable(request.getQueryString()).map(s -> "?" + s).orElse("");

        log.info("Received request: {} {} [{}]", request.getMethod(), request.getRequestURI() + queryParameterString, response.getStatus());
        return true;
    }
}
