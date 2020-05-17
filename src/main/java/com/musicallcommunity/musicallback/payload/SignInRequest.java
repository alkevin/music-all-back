package com.musicallcommunity.musicallback.payload;

import javax.validation.constraints.NotNull;

public class SignInRequest {

    @NotNull(message = "mail must be set")
    private String mail;

    @NotNull(message = "password must be set")
    private String password;

    /**
     * Default constructor
     */
    protected SignInRequest() {
    }

    /**
     * Full constructor
     * @param mail The user email
     * @param password The user password
     */
    public SignInRequest(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
