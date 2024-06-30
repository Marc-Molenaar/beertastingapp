package com.sjoerd69.beertastingapp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class AuthenticationDTO {
    public String name;
    public String email;
    public String password;
    public String address;
    public String city;

    @JsonAlias("postal_code")
    public String postalCode;

    public AuthenticationDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthenticationDTO() {
    }
}
