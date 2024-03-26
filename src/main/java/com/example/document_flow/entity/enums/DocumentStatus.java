package com.example.document_flow.entity.enums;

import lombok.Getter;

@Getter
public enum DocumentStatus {
    ACCEPTED ("Подтвержден"),
    CANCELED ("Отменён"),
    WAITING ("Ожидание"),
    UNKNOWN ("Неизвестен");

    private final String name;

    DocumentStatus(String name) {
        this.name = name;
    }
}
