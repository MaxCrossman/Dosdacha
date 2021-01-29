package ru.itis.maxcrossman.servlets;

import ru.itis.maxcrossman.dto.UserDto;
import ru.itis.maxcrossman.models.Page;
import ru.itis.maxcrossman.models.Post;
import ru.itis.maxcrossman.services.PageListingService;
import ru.itis.maxcrossman.services.PostListingService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    private PostListingService postListingService;
    private PageListingService pageListingService;
    private Optional<Page> board;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.postListingService = (PostListingService) context.getAttribute("postListingService");
        this.pageListingService = (PageListingService) context.getAttribute("pageListingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String boardString = "/" + req.getParameter("board");
        board = pageListingService.getAllBoards()
                .stream()
                .filter(x -> x.getAddress().equals(boardString))
                .findAny();
        if (board.isPresent()) {
            req.setAttribute("boardAddress", "/board" + board.get().getAddress());
            req.setAttribute("boardName", board.get().getName());
            req.setAttribute("warningMessage", new String());
            req.getRequestDispatcher("/jsp/post.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/boards");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String thread = req.getParameter("thread");
        String boardId = req.getParameter("boardId");
        String value = req.getParameter("value");
        if (thread == null || boardId == null) {
            String name = req.getParameter("name");

            if (name.isEmpty() || value.isEmpty()) {
                req.setAttribute("boardAddress", "/board" + board.get().getAddress());
                req.setAttribute("boardName", board.get().getName());
                req.setAttribute("warningMessage", "Fields cannot be empty");
                req.getRequestDispatcher("/jsp/post.jsp").forward(req, resp);
            } else if (name.length() > 200) {
                req.setAttribute("boardAddress", "/board" + board.get().getAddress());
                req.setAttribute("boardName", board.get().getName());
                req.setAttribute("warningMessage", "Thread name cannot contain more than 200 characters");
                req.getRequestDispatcher("/jsp/post.jsp").forward(req, resp);
            } else {
                Post newPost = new Post(
                        null,
                        board.get().getId(),
                        ((UserDto) req.getSession().getAttribute("user")).getId(),
                        name,
                        value,
                        LocalDateTime.now(),
                        true,
                        null);
                Long threadId = postListingService.post(newPost);
                resp.sendRedirect("/board" + board.get().getAddress() + "?thread=" + threadId);
            }
        } else {
            String boardAddress = req.getParameter("boardAddress");
            if (!value.isEmpty()) {
                Post newPost = new Post(
                        null,
                        Long.valueOf(boardId),
                        ((UserDto) req.getSession().getAttribute("user")).getId(),
                        null,
                        value,
                        LocalDateTime.now(),
                        false,
                        Long.valueOf(thread));
                String commentId = postListingService.comment(newPost);
                resp.sendRedirect("/board" + boardAddress + "?thread=" + thread + "#" + commentId);
            }
        }
    }
}


