package com.vitaly.rest_api_no_spring_app.service.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.repository.FileRepository;
import com.vitaly.rest_api_no_spring_app.repository.impl.FileRepositoryImpl;
import com.vitaly.rest_api_no_spring_app.service.FileService;

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
    public List<File> getAll() {
        return fileRepository.getAll();
    }

    @Override
    public File getById(Integer id) {
        return fileRepository.getById(id);
    }

    @Override
    public File create(File file) {
        return fileRepository.create(file);
    }

    @Override
    public File update(File file) {
        return fileRepository.update(file);
    }

    @Override
    public void deleteById(Integer id) {
        fileRepository.deleteById(id);
    }

}
