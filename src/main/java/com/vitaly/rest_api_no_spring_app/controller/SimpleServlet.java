package com.vitaly.rest_api_no_spring_app.controller;
//  20-Jan-24
// gh crazym8nd


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple class that extends {@link HttpServlet}.
 *
 * @author Eugene Suleimanov
 */
public class SimpleServlet extends HttpServlet {

    private String message;
    @Override
    public void init() throws ServletException {
        message = "This is simple servlet message";
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter messageWriter = response.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }

    @Override
    public void destroy() {

    }
}