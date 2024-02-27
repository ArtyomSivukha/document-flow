package com.example.document_flow.entity;

import lombok.Getter;

@Getter
public enum DocumentType {
    DECREE ("Приказ"),
    STATEMENT ("Заявление"),
    PETITION ("Ходатайство");

    private final String name;

    DocumentType(String name) {
        this.name = name;
    }
}


