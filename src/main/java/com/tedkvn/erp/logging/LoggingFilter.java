package com.tedkvn.erp.logging;

import com.tedkvn.erp.cache.CachedHttpServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class LoggingFilter extends AbstractRequestLoggingFilter {

    public LoggingFilter() {
        setIncludeClientInfo(true);
        setIncludeHeaders(true);
        setIncludePayload(true);
        setIncludeQueryString(true);
        setAfterMessagePrefix(new Date() + " ");

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String reqBody = null;
        CachedHttpServletRequest cachedHttpServletRequest = new CachedHttpServletRequest(request);

        // Read the request body into a string
        reqBody =
                IOUtils.toString(cachedHttpServletRequest.getInputStream(), StandardCharsets.UTF_8);

        // remove sensitive information from logging
        reqBody = reqBody.replaceAll("(?<=,)\\s*\"password\":\\s*\".+?(?=\",\\s*)", "");

        log.info("date={} method={} uri={} query={} payload={} ip={}", new Date(),
                request.getMethod(), request.getRequestURI(), request.getQueryString(), reqBody,
                request.getRemoteAddr());

        filterChain.doFilter(cachedHttpServletRequest, response);
    }


    @Override
    protected void beforeRequest(@NotNull HttpServletRequest request, @NotNull String message) {
        //        log.info(message);
    }

    @Override
    protected void afterRequest(@NotNull HttpServletRequest request, @NotNull String message) {

    }

}