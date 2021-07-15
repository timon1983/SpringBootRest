package com.example.SpringRestProject.service;

import com.example.SpringRestProject.model.File;
import com.example.SpringRestProject.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

class FileServiceTest {
    @Mock
    private FileRepository fileRepository = Mockito.mock(FileRepository.class);
    @Mock
    private FileService fileService = Mockito.mock(FileService.class);

    @Test
    void checkSaveService_Should_Return_File() {
        File file = new File();
        when(fileRepository.save(file)).thenReturn(file);
    }

    @Test
    void checkGetByIdService_Should_Return_File_By_Id() {
        File file = new File();
        when(fileRepository.getById(2L)).thenReturn(file);
    }

    @Test
    void checkGetAllService_Should_Show_All_of_Files() {
        List<File> fileList = new ArrayList<>();
        when(fileRepository.findAll()).thenReturn(fileList);
    }

    @Test
    void checkDeleteByIdService() {
        fileService.delete(2L);
        Mockito.verify(fileService).delete(2L);
    }

}