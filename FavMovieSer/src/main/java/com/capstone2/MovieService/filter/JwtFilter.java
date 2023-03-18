package com.capstone2.MovieService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean
{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        String authHeader=httpServletRequest.getHeader("Authorization");
        ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletOutputStream.print("Missing or Invalid Token");
            servletOutputStream.close();
        }else {
            String jwtToken=authHeader.substring(7);
            String userName= Jwts.parser().setSigningKey("Security Key").parseClaimsJws(jwtToken).getBody().getSubject();
            httpServletRequest.setAttribute("Name",userName);
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
