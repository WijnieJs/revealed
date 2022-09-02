package com.example.two.security.jwt;

import com.example.two.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                // This will return a call to the build method in userdetailimpl, build the user and convert roles to
                // authorities

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());

 // UsernamePasswordAuthenticationToken , will write it from here as AuthU , is a subclass of AbstractAuthenticationToken
// the AuthU has 2 Constructors and ,2 static  methods with UsernamePasswordAuthenticationToken(),
// The successfull  constructor needs to be called with   3parameters.
// The static method will be called with  super.setAuthenticated(true) hard coded in, and calls super(autoritries)
// In the parent the authenticated boolean switches too true.
 // In the class we iterate over authorities and set them as a unmodifiableList collection.
  // The class has a method wich can evaluate the InstanceOf getUserPrincipal(), which will
 //wich will either be UserDetails ,, AuthenticatedPrincipal , Principal returning the getName



                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//    WebAuthenticationDetailsSource implements AuthenticationDetailsSource, were / when buildDetails() is called
// wich will build details object from HttpServletRequest creating a new WebAuthenticationDetails


                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
//            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}
