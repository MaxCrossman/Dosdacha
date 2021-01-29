package ru.itis.maxcrossman.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.itis.maxcrossman.models.Page;
import ru.itis.maxcrossman.services.PostListingService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/board/*")
public class BoardServlet extends HttpServlet {

    private PostListingService postListingService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.postListingService = (PostListingService) context.getAttribute("postListingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String thread = req.getParameter("thread");
        if (thread == null) {
            req.getRequestDispatcher("/html/board.html").forward(req, resp);
        } else {
            req.setAttribute("posts", postListingService.getByThreadId(Long.valueOf(thread)));
            Page board = (Page) req.getAttribute("page");
            req.setAttribute("boardId", board.getId());
            req.setAttribute("boardName", board.getName());
            req.setAttribute("boardAddress", board.getAddress());
            req.setAttribute("thread", thread);
            req.getRequestDispatcher("/jsp/thread.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String query = data.get("name").getAsString();
        Page board = (Page) req.getAttribute("page");

        String threads ="";
        if (query.isEmpty()) {
            threads = objectMapper.writeValueAsString(postListingService.getByBoard(board));
        } else {
            threads = objectMapper.writeValueAsString(postListingService.getByBoard(board)
                    .stream()
                    .filter(x -> (x.getName().toLowerCase() + " " + x.getAuthor().toLowerCase()).contains(query.toLowerCase()))
                    .collect(Collectors.toList()));
        }

        resp.getWriter().write(threads);
    }
}


