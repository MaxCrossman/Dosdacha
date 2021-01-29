package ru.itis.maxcrossman.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.itis.maxcrossman.services.PageListingService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/boards")
public class BoardListServlet extends HttpServlet {

    private PageListingService pagesService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.pagesService = (PageListingService) context.getAttribute("pageListingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/boards.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String query = data.get("name").getAsString();

        String boards ="";
        if (query.isEmpty()) {
            boards = objectMapper.writeValueAsString(pagesService.getAllBoards());
        } else {
            boards = objectMapper.writeValueAsString(pagesService.getAllBoards()
                    .stream()
                    .filter(x -> (x.getAddress()+" - " + x.getName().toLowerCase()).contains(query.toLowerCase()))
                    .collect(Collectors.toList()));
        }

        resp.getWriter().write(boards);
    }
}


