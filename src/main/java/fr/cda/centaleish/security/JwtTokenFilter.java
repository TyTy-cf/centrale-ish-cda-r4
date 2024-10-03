package fr.cda.centaleish.security;


import fr.cda.centaleish.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Get the HTTP header "Authorization"
        String authorizedHeader = request.getHeader("Authorization");
        String token;
        String username;

        // Authorization exists and start with "Bearer "
        if (authorizedHeader != null
                && authorizedHeader.startsWith("Bearer ")
                && authorizedHeader.length() > 7
        ) {
            // The user has a token !
            token = authorizedHeader.substring(7);
            username = jwtService.extractUserName(token);
            UserDetails userDetails = userService.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                //  For now : we consider that the user is logged in, we have to log him !
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        // Continue the filter HTTP
        filterChain.doFilter(request, response);
    }
}
