package com.example.document_flow.controller;

import com.example.document_flow.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> handleFileUploadUsingCurl(
            @RequestParam("file") MultipartFile file) throws IOException {

        Map<String, String> map = new HashMap<>();

        storageService.uploadImage(file);
        // Populate the map with file details
        map.put("fileName", file.getOriginalFilename());
        map.put("fileSize", String.valueOf(file.getSize()));
        map.put("fileContentType", file.getContentType());

        // File upload is successful
        map.put("message", "File upload done");
        return ResponseEntity.ok(map);
    }

    @PostMapping()
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        return new ResponseEntity<>(storageService.uploadImage(file), HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
//        return new ResponseEntity<>(storageService.downloadImage(fileName), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(storageService.downloadImage(fileName));
    }
//    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws FileNotFoundException {
//
//        FileInputStream fileInputStream = new FileInputStream(fileName);
//        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.TEXT_PLAIN)
//                .body(inputStreamResource);
//    }

}
