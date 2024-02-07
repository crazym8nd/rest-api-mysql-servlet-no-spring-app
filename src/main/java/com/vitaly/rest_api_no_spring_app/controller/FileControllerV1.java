package com.vitaly.rest_api_no_spring_app.controller;
//  20-Jan-24
// gh crazym8nd

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitaly.rest_api_no_spring_app.dto.FileDto;
import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.service.FileService;
import com.vitaly.rest_api_no_spring_app.service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

@WebServlet("/api/v1/files/*")
@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*11
)
public class FileControllerV1 extends HttpServlet {
    final FileService fileService = new FileServiceImpl();
    final ObjectMapper mapper = new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String pathInfo = req.getPathInfo();

            if (pathInfo == null || pathInfo.equals("/")) {
                List<FileDto> fileList = fileService.getAll();
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(fileList));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 2) {
                    Integer id = Integer.parseInt(pathParts[1]);
                    FileDto file = fileService.getById(id);

                    if (file != null) {

                        resp.setContentType("application/json");
                        resp.getWriter().write(mapper.writeValueAsString(file));
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
            } catch (Exception e){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
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

        resp.getWriter().write("File saved");
        fileContent.close();
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || !pathInfo.startsWith("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length != 2) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            FileDto fileToUpdate = mapper.readValue(req.getInputStream(), FileDto.class);
            fileService.update(fileToUpdate);

            if (fileToUpdate.getId() != -1) {
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || !pathInfo.startsWith("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length != 2) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Integer id = Integer.parseInt(pathParts[1]);

            fileService.deleteById(id);

            if (id == -1) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.getWriter().write("File with id= " + id + " successfully deleted");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
