package ru.itis.maxcrossman.servlets;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ru.itis.maxcrossman.dto.SignInForm;
import ru.itis.maxcrossman.dto.SignUpForm;
import ru.itis.maxcrossman.dto.UserDto;
import ru.itis.maxcrossman.services.SignInService;
import ru.itis.maxcrossman.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;
    private SignInService signInService;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.signUpService = (SignUpService) context.getAttribute("signUpService");
        this.signInService = (SignInService) context.getAttribute("signInService");
        this.validator = (Validator) context.getAttribute("validator");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", new ArrayList<Object>());
        req.getRequestDispatcher("/jsp/sign_up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm form = new SignUpForm(
                req.getParameter("email"),
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("password"));

        Set<ConstraintViolation<SignUpForm>> constraintViolations = validator.validate(form);

        if (!constraintViolations.isEmpty()) {
            req.setAttribute("errors", constraintViolations);
            req.getRequestDispatcher("/jsp/sign_up.jsp").forward(req, resp);
        } else {
            signUpService.signUp(form);
            Optional<UserDto> userDto = signInService.signIn(new SignInForm(form.getEmail(), form.getPassword()));
            HttpSession session = req.getSession();
            session.setAttribute("user", userDto.get());
            System.out.println(session.getId());
            Cookie cookie = new Cookie("sessionID", session.getId());
            cookie.setMaxAge(10000);
            resp.addCookie(cookie);
            resp.sendRedirect("/profile");
        }
    }
}
