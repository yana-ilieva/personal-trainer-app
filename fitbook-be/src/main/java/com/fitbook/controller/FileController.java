package com.fitbook.controller;

import com.fitbook.dto.FileDto;
import com.fitbook.exception.RequestProcessingException;
import com.fitbook.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "http://localhost:8080")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/user/{id}")
    public Boolean get(@PathVariable("id") Long userId, HttpServletResponse response) throws IOException {
        FileDto fileInfo = fileService.getFileInfo(userId);
        if (fileInfo != null) {
            response.setContentType(fileInfo.getMimeType());
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileInfo.getName() + "\"");

            ServletOutputStream outputStream = response.getOutputStream();
            fileService.get(userId, outputStream);
            return true;
        }
        return false;
    }

    @GetMapping
    public Boolean get(HttpServletResponse response) throws IOException {
        FileDto fileInfo = fileService.getFileInfo();
        if (fileInfo != null) {
            response.setContentType(fileInfo.getMimeType());
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileInfo.getName() + "\"");

            ServletOutputStream outputStream = response.getOutputStream();
            fileService.get(fileInfo.getUserId(), outputStream);
            return true;
        }
        return false;
    }

    @GetMapping("/{id}")
    public Boolean get(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
        FileDto fileInfo = fileService.getFile(id);
        if (fileInfo != null) {
            response.setContentType(fileInfo.getMimeType());
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileInfo.getName() + "\"");

            ServletOutputStream outputStream = response.getOutputStream();
            fileService.get(fileInfo.getUserId(), outputStream);
            return true;
        }
        return false;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> save(@RequestPart("file") MultipartFile file) {
        List<String> imageFormats = List.of(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE);
        if (!imageFormats.contains(file.getContentType())) {
            throw new RequestProcessingException("Invalid file format.");
        }
        Boolean result = fileService.save(file);
        return ResponseEntity.ok().body(result);
    }
}
