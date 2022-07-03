package com.fitbook.service;

import com.fitbook.dto.FileDto;
import com.fitbook.entity.File;
import com.fitbook.entity.user.User;
import com.fitbook.exception.RequestProcessingException;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.FileRepository;
import com.fitbook.util.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    private final UserService userService;

    private final FileRepository fileRepository;

    private final Mapper mapper;

    private final AuthService authService;

    @Value("${file.store}")
    private String fileStore;

    public FileService(UserService userService, FileRepository fileRepository, Mapper mapper, AuthService authService) {
        this.userService = userService;
        this.fileRepository = fileRepository;
        this.mapper = mapper;
        this.authService = authService;
    }

    public FileDto getFileInfo(Long userId) {
        User user = userService.findById(userId);
        return mapper.mapFile(user);
    }

    public FileDto getFileInfo() {
        User user = userService.findById(authService.getUserId());
        return mapper.mapFile(user);
    }

    public FileDto getFile(Long id) {
        Optional<File> fileOpt = fileRepository.findById(id);
        if (fileOpt.isEmpty()) {
            throw new ResourceNotFoundException("File is not found");
        }
        return mapper.mapFile(fileOpt.get());
    }

    public void get(Long userId, ServletOutputStream outputStream) {
        User user = userService.findById(userId);

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        String name = user.getProfilePicture().getName();

        String filePath = fileStore + java.io.File.separator + name;

        java.io.File sysFile = new java.io.File(filePath);

        try {
            InputStream inputStream = new FileInputStream(sysFile);
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean save(MultipartFile file) {
        User user = userService.findById(authService.getUserId());

        File oldFile = null;
        if (user.getProfilePicture() != null) {
            oldFile = user.getProfilePicture();
        }

        String fileName = UUID.randomUUID().toString();

        String filePath = fileStore + java.io.File.separator + fileName;

        try {
            java.io.File sysFile = new java.io.File(filePath);
            file.transferTo(sysFile);
            File file1 = new File();
            file1.setName(fileName);
            file1.setFileSize(file.getSize());
            file1.setMimeType(file.getContentType());
            file1.setUser(user);
            user.setProfilePicture(file1);
            userService.save(user);
            deleteFile(oldFile);
            return true;
        } catch (IOException e) {
            java.io.File f  = new java.io.File(filePath);
            try {
                Files.deleteIfExists(f.toPath());
            } catch (IOException ex) {
                throw new RequestProcessingException("Unable to delete file " + f.getName());
            }
            throw new RequestProcessingException("Error while saving file.");
        }
    }

    private void deleteFile(File file) {
        if (file != null) {
            java.io.File sysFile = new java.io.File(fileStore + java.io.File.separator + file.getName());
            boolean result = sysFile.delete();
            if (!result) {
                throw new RequestProcessingException("Unable to delete file " + sysFile.getName());
            }
            fileRepository.delete(file);
        }
    }
}
