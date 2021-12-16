package com.bcp.exchangeRate.adapters.security;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bcp.exchangeRate.application.ports.in.LoggingUseCase;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import static com.bcp.exchangeRate.adapters.security.Constants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private LoggingUseCase loggingUseCase;

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if(loggingUseCase==null){
            ServletContext servletContext = req.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            loggingUseCase = webApplicationContext.getBean(LoggingUseCase.class);
        }
        String header = req.getHeader(HEADER_AUTHORIZATION_KEY);
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws IOException {
        String token = request.getHeader(HEADER_AUTHORIZATION_KEY);
        try {
            String user = Jwts.parser()
                    .setSigningKey(SUPER_SECRET_KEY)
                    .parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""))
                    .getBody()
                    .getSubject();
            loggingUseCase.addLogFromRequest(user,request);
            if (user != null) {
                loggingUseCase.addLogFromRequest(user,request).blockingGet();
                return new UsernamePasswordAuthenticationToken(user, null, null);
            }
        } catch (Exception e) {
            loggingUseCase.addLogFromRequest("Not_Authorizated",request).blockingGet();
            throw e;
        }
        return null;
    }
}