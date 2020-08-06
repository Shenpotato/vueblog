package com.shenpotato.vueblog.common.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {

    @NotBlank(message = "username cannot be blank")
    private String username;

    private String avatar;

    @NotBlank(message = "email cannot be blank")
    @Email(message = "Email format not right")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;

    private String repassword;

}
