package com.tedkvn.erp.logging;

import com.tedkvn.erp.cache.CachedHttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
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
    protected void beforeRequest(@NotNull HttpServletRequest request, @NotNull String message) {
        //        log.info(message);
    }

    @Override
    protected void afterRequest(@NotNull HttpServletRequest request, @NotNull String message) {
        String reqBody = null;
        CachedHttpServletRequest cachedHttpServletRequest = null;
        try {
            cachedHttpServletRequest = new CachedHttpServletRequest(request);
            reqBody = IOUtils.toString(cachedHttpServletRequest.getInputStream(),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            // log.error(e)
        }
        log.info("date={} method={} uri={} query={} payload={} ip={}", new Date(),
                request.getMethod(), request.getRequestURI(), request.getQueryString(), reqBody,
                request.getRemoteAddr());
    }

}