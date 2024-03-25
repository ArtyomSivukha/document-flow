package com.example.document_flow.entity.enums;

import lombok.Getter;

@Getter
public enum DocumentType {
    DECREE ("Приказ"),
    STATEMENT ("Заявление"),
    UNKNOWN ("Неизвестно"),
    PETITION ("Ходатайство");

    private final String name;

    DocumentType(String name) {
        this.name = name;
    }
}


