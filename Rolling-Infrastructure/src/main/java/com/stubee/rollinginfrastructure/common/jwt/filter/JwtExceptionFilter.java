package com.stubee.rollinginfrastructure.common.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stubee.rollingcore.common.exception.ErrorCode;
import com.stubee.rollinginfrastructure.common.exception.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            setErrorResponse(response, ErrorCode.EXPIRED_JWT);
        } catch (MalformedJwtException e) {
            setErrorResponse(response, ErrorCode.MALFORMED_JWT);
        } catch (UnsupportedJwtException e) {
            setErrorResponse(response, ErrorCode.UNSUPPORTED_JWT);
        } catch (IllegalArgumentException e) {
            setErrorResponse(response, ErrorCode.ILLEGAL_ARGUMENT_ERROR);
        }
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode error) {
        try {
            responseToClient(response, getErrorResponse(error.getStatusValue(), error.getMessage()));
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

    private ErrorResponse getErrorResponse(final int httpStatusValue, final String message) {
        return new ErrorResponse(httpStatusValue, message);
    }

}