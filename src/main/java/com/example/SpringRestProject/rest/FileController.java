package com.example.SpringRestProject.rest;

import com.example.SpringRestProject.model.File;
import com.example.SpringRestProject.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/files")
public class FileController {

    private FileService fileService;


    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @RequestMapping(value = "{id}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('file:read')")
    public ResponseEntity<File> getFile(@PathVariable("id") Long fileId){
        if(fileId == null){
            return new ResponseEntity<File>(HttpStatus.BAD_REQUEST);
        }
        File file = fileService.findById(fileId);
        if(file == null){
            return  new ResponseEntity<File>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<File>(file , HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('file:read')")
    public ResponseEntity<List<File>> getAllFiles(){
        List<File> files = fileService.findAll();
        if(files.isEmpty()){
            return new ResponseEntity<List<File>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<File>>(files, HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('file:write')")
    public ResponseEntity<File> saveFile(@RequestBody @Valid File file){
        HttpHeaders headers = new HttpHeaders();
        if(file == null){
            return new ResponseEntity<File>(HttpStatus.BAD_REQUEST);
        }
        fileService.save(file);
        return new ResponseEntity<File>(file,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "" , method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('file:write')")
    public ResponseEntity<File> updateFile(@RequestBody @Valid File file , UriComponentsBuilder builder){
        HttpHeaders headers = new HttpHeaders();
        if(file == null){
            return new ResponseEntity<File>(HttpStatus.BAD_REQUEST);
        }
        fileService.save(file);
        return new ResponseEntity<File>(file,headers,HttpStatus.OK);
    }

    @RequestMapping(value = "{id}" , method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('file:write')")
    public ResponseEntity<File> deleteFile(@PathVariable("id") Long fileId){
        File file = fileService.findById(fileId);
        if(file == null){
            return new ResponseEntity<File>(HttpStatus.NOT_FOUND);
        }
        fileService.delete(fileId);
        return new ResponseEntity<File>(HttpStatus.NO_CONTENT);
    }
}
