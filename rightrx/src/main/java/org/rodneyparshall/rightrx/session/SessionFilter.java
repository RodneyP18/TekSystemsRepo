package org.rodneyparshall.rightrx.session;


import org.rodneyparshall.rightrx.service.CustomUserDetailsService;

import org.rodneyparshall.rightrx.service.implementation.CustomUserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class SessionFilter extends OncePerRequestFilter {

    private final InMemSessionRegistry inMemSessionRegistry;
    private final CustomUserDetailsService customUserDetailsService;

    public SessionFilter(final InMemSessionRegistry inMemSessionRegistry, final CustomUserDetailsService customUserDetailsService) {
        this.inMemSessionRegistry = inMemSessionRegistry;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (sessionId == null || sessionId.length() == 0){
            filterChain.doFilter(request, response);
            return;
        }

        final String username = inMemSessionRegistry.getEmailForSession(sessionId);
        if(username == null){
            filterChain.doFilter(request, response);
            return;
        }

        final CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);

        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                customUserDetails, null, customUserDetails.getAuthorities()

        );

        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);

    }
}
