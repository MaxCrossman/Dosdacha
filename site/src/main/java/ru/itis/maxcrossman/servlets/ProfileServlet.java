package ru.itis.maxcrossman.servlets;

import ru.itis.maxcrossman.dto.UserDto;
import ru.itis.maxcrossman.services.PostListingService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private PostListingService postListingService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        postListingService = (PostListingService) context.getAttribute("postListingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto udto = (UserDto) req.getSession().getAttribute("user");

        req.setAttribute("firstName", udto.getFirstName());
        req.setAttribute("lastName", udto.getLastName());
        req.setAttribute("email", udto.getEmail());
        req.setAttribute("posts", postListingService.getByUserId(udto.getId()));

        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("user", null);
        resp.sendRedirect("/home");
    }
}
