package com.vitaly.rest_api_no_spring_app.controller;
//  20-Jan-24
// gh crazym8nd

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitaly.rest_api_no_spring_app.dto.FileDto;
import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.service.FileService;
import com.vitaly.rest_api_no_spring_app.service.impl.FileServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@WebServlet("/api/v1/files")
@MultipartConfig(
        location = "D:\\testFiles",
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*11
)
public class FileControllerV1 extends HttpServlet {
    final FileService fileService = new FileServiceImpl();
    final ObjectMapper mapper = new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<FileDto> fileList = fileService.getAll();
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(fileList));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Path fileSavePath = Paths.get("D:\\testFiles", fileName);

        InputStream fileContent = filePart.getInputStream();

        Files.copy(fileContent,fileSavePath,StandardCopyOption.REPLACE_EXISTING);

        FileDto fileDto = new FileDto();
        fileDto.setName(fileName);
        fileDto.setFilePath(fileSavePath.toString());
        fileDto.setStatus(Status.ACTIVE);
        fileService.create(fileDto);

        fileContent.close();
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }
}
