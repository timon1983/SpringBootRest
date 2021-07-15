package com.example.SpringRestProject.service;

import com.example.SpringRestProject.model.File;
import com.example.SpringRestProject.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FileService {

    private FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File save(File file){
        return fileRepository.save(file);
    }

    public File findById(long id){
        return fileRepository.getById(id);
    }

    public List<File> findAll(){
        return fileRepository.findAll();
    }

    public void delete(long id){
        fileRepository.deleteById(id);
    }
}
