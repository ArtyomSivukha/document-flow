package com.example.document_flow.dto;

public class SigninDTO {
    private String login;
    private String password;

    public SigninDTO() {
    }

    public SigninDTO(String login, String password) {
        this.login = login;
        this.password = password;
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
}
