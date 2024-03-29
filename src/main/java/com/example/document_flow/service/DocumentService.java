package com.example.document_flow.service;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.User;
import com.example.document_flow.entity.enums.DocumentStatus;
import com.example.document_flow.entity.enums.DocumentType;
import com.example.document_flow.repository.DocumentRepository;
import com.example.document_flow.repository.UserRepository;
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
public class DocumentService implements Getable<Document> {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Document> getDocument(Long id) {
        return documentRepository.findById(id);
    }

    public Document updateDocument(Long id, MultipartFile file) throws IOException {
        try {

            Files.createDirectories(Paths.get(uploadPath));

            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath, fileName);
            file.transferTo(filePath);

            Document document = documentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + id));
            Files.deleteIfExists(Paths.get(document.getFilePath()));
            document.setName(file.getOriginalFilename());
            document.setFilePath(filePath.toString());
            document.setType(file.getContentType());
            return documentRepository.save(document);
        } catch (IOException ignored) {
            return null;
        }
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Файл не найден с id " + id));
    }

    @Override
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document getOne(Long id) {
        return documentRepository.findById(id).get();
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
            document.setDocumentType(DocumentType.STATEMENT);
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
        User user = userRepository.findById(CurrentUser.getInstance().getUser().getId()).get();
        document.getShares().remove(user);
        user.getShareDocuments().remove(document);
        documentRepository.save(document);
        documentRepository.deleteById(id);
        return "Документ с ID = " + id + " удален";
    }

    public User share(Long documentId, String userLogin) {
        User userToShare = userRepository.findByLogin(userLogin).get();
        userToShare.getShareDocuments().add(documentRepository.findById(documentId).get());
        return userRepository.save(userToShare);
    }

    public Document confirm(Long id, DocumentStatus documentStatus) {
        Document preparedDocument = documentRepository.findById(id).get();
        preparedDocument.setDocumentStatus(documentStatus);
        return documentRepository.save(preparedDocument);
    }

    public List<Document> getFromCurrentUser() {
        User currentUser = userRepository.findById(CurrentUser.getInstance().getUser().getId()).get();
        return currentUser.getDocuments();
    }

    public List<Document> getShared() {
        User currentUser = userRepository.findById(CurrentUser.getInstance().getUser().getId()).get();
        return currentUser.getShareDocuments();
    }
}
