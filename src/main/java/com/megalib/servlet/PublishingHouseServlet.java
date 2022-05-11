package com.megalib.servlet;

import com.megalib.service.PublishingHouseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/publish")
public class PublishingHouseServlet extends HttpServlet {

    private final PublishingHouseService publishingHouseService = PublishingHouseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("publishingHouses", publishingHouseService.findAll());

        req.getRequestDispatcher("/WEB-INF/jsp/publishingHouses.jsp").forward(req, resp);
    }
}
