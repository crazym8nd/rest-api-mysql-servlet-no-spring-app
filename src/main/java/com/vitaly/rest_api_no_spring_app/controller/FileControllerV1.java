package com.vitaly.rest_api_no_spring_app.controller;
//  20-Jan-24
// gh crazym8nd

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vitaly.rest_api_no_spring_app.dto.EventDto;
import com.vitaly.rest_api_no_spring_app.dto.FileDto;
import com.vitaly.rest_api_no_spring_app.service.EventService;
import com.vitaly.rest_api_no_spring_app.service.FileService;
import com.vitaly.rest_api_no_spring_app.service.impl.EventServiceImpl;
import com.vitaly.rest_api_no_spring_app.service.impl.FileServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/v1/files")
public class FileControllerV1 extends HttpServlet {
    final FileService fileService = new FileServiceImpl();
    final ObjectMapper mapper = new ObjectMapper();





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<FileDto> fileList = fileService.getAll();

        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(fileList));
    }
}
