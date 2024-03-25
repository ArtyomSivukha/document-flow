package com.example.document_flow.service;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.enums.DocumentType;
import com.example.document_flow.repository.DocumentRepository;
import com.example.document_flow.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Service
public class DocumentService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(MultipartFile file) throws IOException {
        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
//        document.setContent(file.getBytes());
        document.setAuthor(CurrentUser.getInstance().getUser());
        return documentRepository.save(document);
    }

    public Optional<Document> getDocument(Long id) {
        return documentRepository.findById(id);
    }

    public Document updateDocument(Long id, MultipartFile file) throws IOException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + id));
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
//        document.setContent(file.getBytes());
        return documentRepository.save(document);
    }

    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    public String newUpload(MultipartFile file) {
        if (file.isEmpty()) {
            return "Файл не выбран";
        }
        try {

            Files.createDirectories(Paths.get(uploadPath));

            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath, fileName);
            file.transferTo(filePath);

            Document document = new Document(fileName, filePath.toString());
            document.setDocumentType(DocumentType.UNKNOWN);
            document.setAuthor(CurrentUser.getInstance().getUser());
            documentRepository.save(document);

            return "Файл успешно загружен";
        } catch (IOException e) {
            return "Ошибка при загрузке файла: " + e.getMessage();
        }
    }

    public String deleteById(Long id) throws IOException {
        Document document = documentRepository.findById(id).get();
        Files.deleteIfExists(Paths.get(document.getFilePath()));
        documentRepository.deleteById(id);
        return "Документ с ID = " + id + " удален";
    }
}
