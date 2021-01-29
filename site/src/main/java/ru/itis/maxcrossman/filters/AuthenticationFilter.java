package ru.itis.maxcrossman.filters;


import ru.itis.maxcrossman.models.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName="AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        Boolean isAuthenticated = false;
        Boolean sessionExists = session != null;

        Boolean isRequestOnOpenPage = ((Page)request.getAttribute("page")).isOpen();
        Boolean isRequestOnCredsPage = ((Page)request.getAttribute("page")).getAddress().equals("/signup") ||
                ((Page)request.getAttribute("page")).getAddress().equals("/signin");

        if (sessionExists) {
            isAuthenticated = session.getAttribute("user") != null;
        }

        if (isAuthenticated && !isRequestOnCredsPage || !isAuthenticated && isRequestOnOpenPage) {
            filterChain.doFilter(request, response);
        } else if(isAuthenticated && isRequestOnCredsPage) {
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/signin");
        }

    }

    @Override
    public void destroy() {

    }
}
