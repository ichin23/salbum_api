package com.ichin23.salbum.infra.security;

import com.ichin23.salbum.domain.user.User;
import com.ichin23.salbum.repositories.UserRepository;
import com.ichin23.salbum.services.TokenService;
import com.ichin23.salbum.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token =this.recoverToken(request);
        if (token!=null){
            var id =tokenService.validateToken(token);
            UUID uuid = UUID.fromString(id);
            var user = userService.findUserById(uuid);
            System.out.println(user);
            if (user.isPresent()){
                var authentication =new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    protected String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader==null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
