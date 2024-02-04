package com.vitaly.rest_api_no_spring_app.util.mappers;
//  25-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.FileDto;
import com.vitaly.rest_api_no_spring_app.model.File;

public class FileMapper {
    public static FileDto convertEntityToDto(File file) {
        FileDto fileDto = new FileDto();
        if (file != null) {
            fileDto.setId(file.getId());
            fileDto.setName(file.getName());
            fileDto.setFilePath(file.getFilePath());
            fileDto.setStatus(file.getStatus());
        } else {
            fileDto = null;
        }
        return fileDto;
    }

    public static File convertDtoToEntity(FileDto fileDto) {
        File file = new File();
        if (fileDto != null) {
            file.setId(fileDto.getId());
            file.setName(fileDto.getName());
            file.setFilePath(fileDto.getFilePath());
            file.setStatus(fileDto.getStatus());
        } else {
           file = null;
        }
        return file;
    }
}
