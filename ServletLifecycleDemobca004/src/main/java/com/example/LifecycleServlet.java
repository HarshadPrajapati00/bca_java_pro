package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Servlet init() called — Servlet Initialized.");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("service() method called — Request Received.");

        // must call super.service to forward the request to doGet/doPost
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("doGet() executed — Handling GET Request.");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<h2>Servlet Lifecycle Demo</h2>");
        out.println("<p>Check your server console for lifecycle messages.</p>");
    }

    @Override
    public void destroy() {
        System.out.println("destroy() called — Servlet Destroyed.");
    }
}

