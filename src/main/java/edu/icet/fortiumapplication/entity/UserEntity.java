package edu.icet.fortiumapplication.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "first name cannot be null")
    @Column( nullable = false)
    private String firstName;

    @NotBlank(message = "last name cannot be null")
    @Column( nullable = false)
    private String lastName;

    @NotBlank(message = "password cannot be null")
    @Column( nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @NotEmpty(message = "at least one role should provided")
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRoleEntity> roles=new HashSet<>();
    private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime updatedAt=null;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getRole())).toList();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
