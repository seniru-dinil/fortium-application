package edu.icet.fortiumapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Set<String> roles;
    private String department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
