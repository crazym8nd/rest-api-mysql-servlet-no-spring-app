package com.vitaly.rest_api_no_spring_app.controller;
//  20-Jan-24
// gh crazym8nd

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vitaly.rest_api_no_spring_app.dto.UserDto;
import com.vitaly.rest_api_no_spring_app.service.UserService;
import com.vitaly.rest_api_no_spring_app.service.impl.UserServiceImpl;
import com.vitaly.rest_api_no_spring_app.util.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/v1/users")
public class UserControllerV1 extends HttpServlet {
    final UserService userService = new UserServiceImpl();

    final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
        List<UserDto> userList = userService.getAll();
        String json = mapper.writeValueAsString(userList);

        resp.setContentType("application/json");

        resp.getWriter().write(json);
    } catch (Exception e) {
        resp.getWriter().write(e.getMessage());}
    }
}
