package ru.itis.maxcrossman.servlets;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ru.itis.maxcrossman.dto.PageForm;
import ru.itis.maxcrossman.services.PageListingService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet("/newboard")
public class NewBoardServlet extends HttpServlet {

    private PageListingService pageListingService;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.pageListingService = (PageListingService) context.getAttribute("pageListingService");
        this.validator = (Validator) context.getAttribute("validator");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", new ArrayList<Object>());
        req.getRequestDispatcher("/jsp/newboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getParameter("address").toLowerCase().trim();
        String name = req.getParameter("name").trim();

        PageForm form = new PageForm(name, address);

        Set<ConstraintViolation<PageForm>> constraintViolations = validator.validate(form);

        if (!constraintViolations.isEmpty()) {
            req.setAttribute("errors", constraintViolations);
            req.getRequestDispatcher("/jsp/newboard.jsp").forward(req, resp);
        } else {
            pageListingService.savePage(form);
            resp.sendRedirect("/board/" + address);
        }
    }
}


