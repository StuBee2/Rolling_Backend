package com.stubee.oauth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stubee.rollingdomains.common.error.CustomException;
import com.stubee.rollingdomains.common.error.ErrorCode;
import com.stubee.rollingdomains.common.error.ErrorResponse;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            log.error("CustomException message : {}", e.getMessage());

            setErrorResponse(response, e.getErrorCode());
        } catch (SignatureException e) {
            log.error("SignatureException message : {}", e.getMessage());

            setErrorResponse(response, ErrorCode.JWT_SIGNATURE_NOT_MATCHED);
        } catch (Exception e) {
            log.error("ErrorName : {}", e.getClass().getSimpleName());
            log.error("ErrorMessage : {}", e.getMessage());

            setErrorResponse(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode error) {
        try {
            responseToClient(response, ErrorResponse.create(error));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void responseToClient(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setStatus(errorResponse.status());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}