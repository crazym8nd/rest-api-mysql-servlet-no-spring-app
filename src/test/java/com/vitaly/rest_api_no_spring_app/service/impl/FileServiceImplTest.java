package com.vitaly.rest_api_no_spring_app.service.impl;

import com.vitaly.rest_api_no_spring_app.dto.FileDto;
import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.model.Status;
import com.vitaly.rest_api_no_spring_app.repository.FileRepository;
import com.vitaly.rest_api_no_spring_app.repository.impl.FileRepositoryImpl;
import com.vitaly.rest_api_no_spring_app.service.FileService;
import com.vitaly.rest_api_no_spring_app.util.mappers.FileMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//  03-Feb-24
// gh crazym8nd

class FileServiceImplTest {
    private final FileRepository fileRepMock = mock(FileRepositoryImpl.class);
    private final FileService fileService = new FileServiceImpl(fileRepMock);

    private File mockFile = new File(1,"fakename1", "fakepath1", Status.ACTIVE);
    private File mockFile2 = new File(2,"fakename2", "fakepath2", Status.ACTIVE);

    private List<File> mockFilesList = List.of(mockFile, mockFile2);

    //positive tests
    @Test
    void getAllSuccess() {
        when(fileRepMock.getAll()).thenReturn(mockFilesList);
        List<FileDto> result = fileService.getAll();

        assertNotNull(result);
        assertEquals(mockFilesList.size(), result.size());

        for(int i=0;i<mockFilesList.size();i++){
            File file = mockFilesList.get(i);
            FileDto fileDto = result.get(i);
            assertEquals(file.getId(), fileDto.getId());
            assertEquals(file.getName(), fileDto.getName());
            assertEquals(file.getFilePath(), fileDto.getFilePath());
            assertEquals(file.getStatus(), fileDto.getStatus());
        }
    }

    @Test
    void getByIdSuccess() {
        when(fileRepMock.getById(1)).thenReturn(mockFile);
        fileService.getById(1);

        assertEquals(mockFile.getId(), fileService.getById(1).getId());
        assertEquals(mockFile.getName(), fileService.getById(1).getName());
        assertEquals(mockFile.getFilePath(), fileService.getById(1).getFilePath());
        assertEquals(mockFile.getStatus(), fileService.getById(1).getStatus());

    }

    @Test
    void createSuccess() {
        when(fileRepMock.create(mockFile)).thenReturn(mockFile);

        FileDto fileDto = fileService.create(FileMapper.convertEntityToDto(mockFile));

        assertEquals(mockFile.getId(), fileDto.getId());
        assertEquals(mockFile.getName(), fileDto.getName());
        assertEquals(mockFile.getFilePath(), fileDto.getFilePath());
    }

    @Test
    void updateSuccess() {
        when(fileRepMock.update(mockFile)).thenReturn(mockFile);

        FileDto fileDto = fileService.update(FileMapper.convertEntityToDto(mockFile));

        assertEquals(mockFile.getId(), fileDto.getId());
        assertEquals(mockFile.getName(), fileDto.getName());
        assertEquals(mockFile.getFilePath(), fileDto.getFilePath());
    }

    @Test
    void deleteByIdSuccess() {
        fileService.deleteById(1);
        verify(fileRepMock).deleteById(1);
    }

    //negative tests
    @Test
    void getAllNegative() {
        when(fileRepMock.getAll()).thenReturn(emptyList());
        List<FileDto> result = fileService.getAll();
        assertTrue(result.isEmpty());


    }

    @Test
    void getByIdNegative() {
        int nonExistingId = 999;
        when(fileRepMock.getById(nonExistingId)).thenReturn(new File(-1, "NO SUCH FILE", null, Status.ACTIVE));
        FileDto result = fileService.getById(nonExistingId);
        assertNotNull(result);
        assertEquals(-1, result.getId());
        assertEquals("NO SUCH FILE", result.getName());
        assertEquals(Status.ACTIVE, result.getStatus());
    }

    @Test
    void createNegative() {
        FileDto fileDtoToSave = new FileDto();
        assertNull(fileRepMock.create(FileMapper.convertDtoToEntity(fileDtoToSave)));
    }

    @Test
    void updateNegative() {

        FileDto nonExistentFile = new FileDto();
        when(fileRepMock.update(FileMapper.convertDtoToEntity(nonExistentFile))).thenReturn(null);
        assertNull(fileRepMock.update(FileMapper.convertDtoToEntity(nonExistentFile)));
    }

    @Test
    void deleteByIdNegative() {

        fileService.deleteById(999);
        verify(fileRepMock).deleteById(999);
    }

}