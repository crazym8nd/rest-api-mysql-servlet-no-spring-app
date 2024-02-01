package com.vitaly.rest_api_no_spring_app.service.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.FileDto;
import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.repository.FileRepository;
import com.vitaly.rest_api_no_spring_app.repository.impl.FileRepositoryImpl;
import com.vitaly.rest_api_no_spring_app.service.FileService;
import com.vitaly.rest_api_no_spring_app.util.mappers.EventMapper;
import com.vitaly.rest_api_no_spring_app.util.mappers.FileMapper;

import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    public FileServiceImpl(){
        this.fileRepository = new FileRepositoryImpl();
    }

    @Override
    public List<FileDto> getAll() {
        List<File> files = fileRepository.getAll();
        List<FileDto> fileDtoList = new ArrayList<>();
        for(File file:files){
            FileDto fileDto = FileMapper.convertEntityToDto(file);
            fileDtoList.add(fileDto);
        }

        return fileDtoList;
    }

    @Override
    public FileDto getById(Integer integer) {
        File file = fileRepository.getById(integer);
        return FileMapper.convertEntityToDto(file);
    }

    @Override
    public FileDto create(FileDto fileDto) {
        File file = FileMapper.convertDtoToEntity(fileDto);
        fileRepository.create(file);
        return fileDto;
    }

    @Override
    public FileDto update(FileDto fileDto) {
        File file = fileRepository.getById(fileDto.getId());
        if (file == null) {
            fileDto.setName("File not found");
            fileDto.setId(-1);
            return fileDto;
        }

        file.setName(fileDto.getName());
        file.setFilePath(fileDto.getFilePath());
        file.setStatus(fileDto.getStatus());
        fileRepository.update(file);
        return fileDto;
    }

    @Override
    public void deleteById(Integer integer) {
        fileRepository.deleteById(integer);
    }
}
