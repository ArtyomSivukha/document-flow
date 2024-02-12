package com.example.document_flow.dto;

import com.example.document_flow.entity.Permission;

public class SignupDTO {
    private String login;
    private String password;
    private String repeatedPassword;
    private Permission permission;

    public SignupDTO() {
    }

    public SignupDTO(String login, String password, String repeatedPassword, Permission permission) {
        this.login = login;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.permission = permission;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
