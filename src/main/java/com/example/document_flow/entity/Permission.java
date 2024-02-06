package com.example.document_flow.entity;

public enum Permission {
    CHIEF_EXECUTIVE_OFFICER ("Исполнительный директор"),
    HUMAN_RESOURCES ("Рекрутор"),
    SUPERVISOR ("Начальник отдела"),
    EMPLOYEE ("Сотрудник"),

    ADMIN ("Администратор системы");
    private String name;

    Permission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
