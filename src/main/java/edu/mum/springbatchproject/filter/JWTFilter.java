package edu.mum.springbatchproject.filter;

import edu.mum.springbatchproject.domain.Person;
import edu.mum.springbatchproject.repository.PersonRepository;
import edu.mum.springbatchproject.util.JWTUtil;
import edu.mum.springbatchproject.util.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component

public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PersonRepository myUserDetail;



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorization != null && authorization.startsWith("Bearer ")) {
            jwt = authorization.substring(7);
            username = jwtUtil.getUsernameFromToken(jwt);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {

            Person pesron = myUserDetail.findByUsername(username).get();

             UserDetails userDetails = new UserSecurity (pesron);

            if (jwtUtil.validateToken(jwt, userDetails)){

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }


        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}