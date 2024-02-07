package com.vitaly.rest_api_no_spring_app.controller;
//  20-Jan-24
// gh crazym8nd

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitaly.rest_api_no_spring_app.dto.UserDto;
import com.vitaly.rest_api_no_spring_app.service.UserService;
import com.vitaly.rest_api_no_spring_app.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/v1/users/*")
public class UserControllerV1 extends HttpServlet {
    final UserService userService = new UserServiceImpl();
    final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String pathInfo = req.getPathInfo();

            if (pathInfo == null || pathInfo.equals("/")) {
                List<UserDto> userList = userService.getAll();
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(userList));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    Integer id = Integer.parseInt(pathParts[1]);
                    UserDto user = userService.getById(id);

                    if (user != null) {
                        resp.setContentType("application/json");
                        resp.getWriter().write(mapper.writeValueAsString(user));
                        resp.setStatus(HttpServletResponse.SC_OK);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            UserDto userToCreate = mapper.readValue(req.getInputStream(), UserDto.class);
            UserDto createdUser = userService.create(userToCreate);

            if (createdUser.getId() != null) {
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(createdUser));
                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        String[] splits = pathInfo.split("/");
        resp.setContentType("application/json");
        if (splits.length == 2) {
            String idStr = splits[1];
            try {
                Integer id = Integer.parseInt(idStr);
                userService.deleteById(id);

                if (userService.getById(id).getId() == -1) {
                    resp.getWriter().write("{\"error\":\"User not found.\"}");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    resp.getWriter().write("{\"message\":\"User successfully deleted.\"}");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        String[] splits = pathInfo.split("/");
        if (splits.length == 2) {
            String idStr = splits[1];
            try {
                Integer id = Integer.parseInt(idStr);
                UserDto userToUpdate = mapper.readValue(req.getInputStream(), UserDto.class);

                userService.update(userToUpdate);

                if (userToUpdate.getId() != -1) {
                    resp.setContentType("application/json");
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(mapper.writeValueAsString(userToUpdate));
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

}
