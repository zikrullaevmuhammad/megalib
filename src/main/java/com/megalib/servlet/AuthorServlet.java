package com.megalib.servlet;

import com.megalib.service.AuthorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {

    private final AuthorService authorService = AuthorService.getInstance();

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("authors", authorService.findAll());

        req.getRequestDispatcher("/WEB-INF/jsp/authors.jsp").forward(req, resp);
    }
}
