package edu.icet.fortiumapplication.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDTO {

    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "first name cannot be null")
    private String firstName;

    @NotBlank(message = "last name cannot be null")
    private String lastName;

    @NotBlank(message = "department cannot be null")
    private String department;

    @NotBlank(message = "password cannot be null")
    private String password;

    @NotEmpty(message = "at least one role should provided")
    private Set<String> roles;
    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime updatedAt=null;


}
