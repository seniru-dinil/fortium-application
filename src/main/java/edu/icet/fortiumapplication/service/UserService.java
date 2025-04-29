package edu.icet.fortiumapplication.service;


import edu.icet.fortiumapplication.dto.RegisterUserRequestDTO;
import edu.icet.fortiumapplication.dto.UserDTO;
import edu.icet.fortiumapplication.entity.UserEntity;
import edu.icet.fortiumapplication.entity.UserRoleEntity;
import edu.icet.fortiumapplication.exception.ResourceNotFoundException;
import edu.icet.fortiumapplication.repository.DepartmentRepository;
import edu.icet.fortiumapplication.repository.UserRepository;
import edu.icet.fortiumapplication.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;
    private final UserRoleRepository userRoleRepository;


    public UserDTO create(RegisterUserRequestDTO requestDTO){
        UserEntity userEntity = mapToUserEntity(requestDTO);
        UserEntity save = userRepository.save(userEntity);
        return mapToUserDTO(save);
    }

    public Set<UserRoleEntity> getUserRoles(Set<String> roles){
        return roles.stream().map(role->userRoleRepository.findByRole(role).orElseThrow(()->new ResourceNotFoundException("ROLE NOT FOUND"))).collect(Collectors.toSet());
    }

    public UserEntity mapToUserEntity(RegisterUserRequestDTO requestDTO){
        return  UserEntity.builder()
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .createdAt(requestDTO.getCreatedAt())
                .updatedAt(null)
                .roles(getUserRoles(requestDTO.getRoles()))
                .department(departmentRepository.findByName(requestDTO.getDepartment()).orElseThrow(()->new ResourceNotFoundException("DEPARTMENT DOES NOT EXIST")))
                .build();
    }


    public UserDTO mapToUserDTO(UserEntity userEntity){
        return UserDTO.builder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .department(userEntity.getDepartment().getName())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .roles(userEntity.getRoles().stream().map(UserRoleEntity::getRole).collect(Collectors.toSet()))
                .build();
    }


}
