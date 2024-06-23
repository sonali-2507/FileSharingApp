package com.filesharingapp.filesharingapp.services;

import com.filesharingapp.filesharingapp.models.FileDb;
import com.filesharingapp.filesharingapp.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService implements IFileStorageService{

    @Autowired
    private FileRepository fileRepository;

    public FileDb store(MultipartFile file) throws IOException {
        System.out.println("Data size: " + file.getBytes().length);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDb FileDb = new FileDb(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(FileDb);
    }

    public FileDb getFile(Long id) {
        return fileRepository.findById(id).get();
    }

    public Stream<FileDb> getAllFiles() {
        return fileRepository.findAll().stream();
    }
}