package edu.icet.fortiumapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequestDTO {
    private String firstName;
    private String lastName;
    private String department;
    private LocalDateTime updatedAt=LocalDateTime.now();
}
