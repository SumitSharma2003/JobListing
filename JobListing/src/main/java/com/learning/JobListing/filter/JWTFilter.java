package com.learning.JobListing.filter;

import com.learning.JobListing.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService service;
    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        String token=null;
        String username=null;
        if(authHeader!=null&&authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            username=service.extractUsername(token);

        }
        System.out.println(username);
        if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=context.getBean(UserDetailsService.class).loadUserByUsername(username);
            System.out.println(userDetails.getUsername());
            if(service.validate(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

               SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("I am here");
                System.out.println("USERNAME = " + username);
                System.out.println("AUTHORITIES = " + userDetails.getAuthorities());
                System.out.println("AUTHENTICATION = " +
                        SecurityContextHolder.getContext().getAuthentication());

            }


        }

        filterChain.doFilter(request,response);

    }
}
