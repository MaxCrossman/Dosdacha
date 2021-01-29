package ru.itis.maxcrossman.servlets;

import ru.itis.maxcrossman.dto.SignInForm;
import ru.itis.maxcrossman.dto.UserDto;
import ru.itis.maxcrossman.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.signInService = (SignInService) context.getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("warningMessage", new String());
        req.getRequestDispatcher("/jsp/sign_in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInForm form = new SignInForm(req.getParameter("email"), req.getParameter("password"));

        Optional<UserDto> userDto = signInService.signIn(form);

        if (userDto.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userDto.get());
            System.out.println(session.getId());
            Cookie cookie = new Cookie("sessionID", session.getId());
            cookie.setMaxAge(10000);
            resp.addCookie(cookie);
            resp.sendRedirect("/profile");
        } else {
            req.setAttribute("warningMessage", "Credentials are invalid");
            req.getRequestDispatcher("/jsp/sign_in.jsp").forward(req, resp);
        }
    }
}
