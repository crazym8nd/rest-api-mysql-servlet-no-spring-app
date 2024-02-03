package com.vitaly.rest_api_no_spring_app.controller;
//  20-Jan-24
// gh crazym8nd

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitaly.rest_api_no_spring_app.dto.EventDto;
import com.vitaly.rest_api_no_spring_app.service.EventService;
import com.vitaly.rest_api_no_spring_app.service.impl.EventServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/api/v1/events/*")
public class EventControllerV1 extends HttpServlet {
    final EventService eventService = new EventServiceImpl();
    final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {

            List<EventDto> eventList = eventService.getAll();
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(eventList));
        } else {

            String[] splits = pathInfo.split("/");
            if (splits.length == 2) {
                int eventId = Integer.parseInt(splits[1]);
                EventDto event = eventService.getById(eventId);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(event));
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EventDto event = mapper.readValue(req.getReader(), EventDto.class);
        eventService.create(event);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write(mapper.writeValueAsString(event));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] splits = pathInfo.split("/");
            if (splits.length == 2) {
                int eventId = Integer.parseInt(splits[1]);
                EventDto eventToUpdate = mapper.readValue(req.getReader(), EventDto.class);
                eventService.update(eventToUpdate);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            String[] splits = pathInfo.split("/");
            if (splits.length == 2) {
                int eventId = Integer.parseInt(splits[1]);
                eventService.deleteById(eventId);
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

}

