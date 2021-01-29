package ru.itis.maxcrossman.filters;


import ru.itis.maxcrossman.models.Page;
import ru.itis.maxcrossman.services.PageListingService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName="PageFilter")
public class PageFilter implements Filter {

    private PageListingService pageListingService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        pageListingService = (PageListingService) filterConfig.getServletContext().getAttribute("pageListingService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Optional<Page> currentPage = pageListingService.getAll()
                .stream()
                .filter(x -> x.getAddress().equals(
                        request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/'))))
                .findAny();

        if (currentPage.isPresent()) {
            request.setAttribute("page", currentPage.get());
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/home");
        }

    }

    @Override
    public void destroy() {

    }
}
