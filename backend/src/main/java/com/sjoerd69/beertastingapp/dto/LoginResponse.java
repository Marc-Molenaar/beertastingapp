package com.sjoerd69.beertastingapp.dto;

import com.sjoerd69.beertastingapp.models.CustomUser;

public class LoginResponse {
    public String email;
    public String token;
    public CustomUser user;

    public LoginResponse(String email, String token, CustomUser user) {
        this.email = email;
        this.token = token;
        this.user = user;
    }
}
