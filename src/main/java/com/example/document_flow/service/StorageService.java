package com.example.document_flow.service;

import com.example.document_flow.entity.Document;
import com.example.document_flow.repository.StorageRepository;
import com.example.document_flow.util.FileUtil;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {
        File filePath = new File("Save");
        filePath.mkdir();
//        File file = new File(filePath + "\\test.txt");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Document imageData = repository.save(Document.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .location(file.getResource().getURL().getPath())
                .build());
        if (imageData != null) {
            return "file uploaded :" + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) {
        Optional<Document> dbFileData =
                repository.findByName(fileName);
        return FileUtil.decompressImage(dbFileData.get().toString().getBytes());
    }
}
