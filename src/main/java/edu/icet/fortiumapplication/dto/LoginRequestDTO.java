package edu.icet.fortiumapplication.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @Email(message = "not a valid email")
    private String email;
    @NotBlank(message = "password field cannot be empty")
    private String password;
}
