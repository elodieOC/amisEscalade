package com.elo.oc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ResetPassForm {
    @NotBlank
    @Email
    private String email;

    public ResetPassForm() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
