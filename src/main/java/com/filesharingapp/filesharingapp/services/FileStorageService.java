package com.filesharingapp.filesharingapp.services;

import com.filesharingapp.filesharingapp.Exceptions.FileStorageException;
import com.filesharingapp.filesharingapp.Exceptions.ResourceNotFoundException;
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
//        System.out.println("Data size: " + file.getBytes().length);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDb fileDb = new FileDb(fileName, file.getContentType(), file.getBytes());
        fileDb.setVersion(1L); //set version
        return fileRepository.save(fileDb);
    }

    public FileDb getFile(Long id) {
        return fileRepository.findById(id).get();
    }

    public Stream<FileDb> getAllFiles() {
        return fileRepository.findAll().stream();
    }
    public FileDb update(Long id, MultipartFile newFile) throws ResourceNotFoundException, FileStorageException {
    FileDb existingFile = fileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("File not found with id " + id));
    try {
        existingFile.setData(newFile.getBytes());
        existingFile.setName(newFile.getOriginalFilename());
        existingFile.setType(newFile.getContentType());
        existingFile.setVersion(existingFile.getVersion() + 1); // increment the version
        return fileRepository.save(existingFile);
    } catch (IOException e) {
        throw new FileStorageException("Could not store file " + newFile.getOriginalFilename());
    }
}
//public FileDb storeFile(MultipartFile file) {
//    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//    try {
//        if (fileName.contains("..")) {
//            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//        }
//        FileDb dbFile = new FileDb(fileName, file.getContentType(), file.getBytes());
//        dbFile.setVersion(1); // set the version to 1
//        return fileRepository.save(dbFile);
//    } catch (IOException ex) {
//        throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//    }
//}
}