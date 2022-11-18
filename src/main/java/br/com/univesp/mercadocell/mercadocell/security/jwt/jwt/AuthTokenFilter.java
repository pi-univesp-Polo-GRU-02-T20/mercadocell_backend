package br.com.univesp.mercadocell.mercadocell.security.jwt.jwt;

import br.com.univesp.mercadocell.mercadocell.security.jwt.AuthenticationFailedException;
import br.com.univesp.mercadocell.mercadocell.security.jwt.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    private static final List<String> allowedPaths = List.of("swagger", "api/docs", "api-docs","/auth/login", "/catalogo-produto",
            "/imagem");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!isProtectedPath(request) || isOptionsRequest(request))
            filterChain.doFilter(request, response);
        else {
            try {
                String jwt = parseJwt(request);
                if (jwtUtils.validateJwtToken(jwt)) {
                    String username = jwtUtils.getUserNameFromJwtToken(jwt);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                filterChain.doFilter(request, response);

            } catch (Exception e) {
                resolver.resolveException(request, response, e.getMessage(), new AuthenticationFailedException("BAD JWT"));
            }
        }
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        throw new AuthenticationFailedException("Header Authorization not found");
    }

    private boolean isProtectedPath(HttpServletRequest request) {
        var isProtected = true;
        for (String path : allowedPaths) {
            if (request.getRequestURI().contains(path))
                isProtected = false;
        }
        return isProtected;
    }

    private boolean isOptionsRequest(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString());
    }


}
