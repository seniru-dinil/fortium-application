package edu.icet.fortiumapplication.service;


import edu.icet.fortiumapplication.dto.LoginRequestDTO;
import edu.icet.fortiumapplication.dto.LoginResponseDTO;
import edu.icet.fortiumapplication.dto.RegisterUserRequestDTO;
import edu.icet.fortiumapplication.dto.UserDTO;
import edu.icet.fortiumapplication.entity.UserEntity;
import edu.icet.fortiumapplication.entity.UserRoleEntity;
import edu.icet.fortiumapplication.exception.ResourceNotFoundException;
import edu.icet.fortiumapplication.exception.UserNotFoundException;
import edu.icet.fortiumapplication.repository.DepartmentRepository;
import edu.icet.fortiumapplication.repository.UserRepository;
import edu.icet.fortiumapplication.repository.UserRoleRepository;
import edu.icet.fortiumapplication.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DepartmentRepository departmentRepository;
    private final UserRoleRepository userRoleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserDTO create(RegisterUserRequestDTO requestDTO){
        UserEntity userEntity = mapToUserEntity(requestDTO);
        UserEntity save = userRepository.save(userEntity);
        return mapToUserDTO(save);
    }

    public UserDTO delete(Integer id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("INVALID USER ID"));
        userRepository.delete(userEntity);
        return mapToUserDTO(userEntity);
    }

    public List<UserDTO> fetchAll(){
        return userRepository.findAll().stream().map(this::mapToUserDTO).toList();
    }

    public UserDTO fetchById(Integer id){
        return mapToUserDTO(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("INVALID USER ID")));
    }



    public List<UserDTO> fetchAll(String keyword){
        return userRepository.findByEmailContainingIgnoreCase(keyword).stream().map(this::mapToUserDTO).toList();
    }

    public List<UserDTO> fetchAllByDepartment(String department){
        return userRepository.findByDepartment(departmentRepository.findByName(department).orElseThrow(()->new ResourceNotFoundException("DEPARTMENT NOT FOUND"))).stream().map(this::mapToUserDTO).toList();
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
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .department(userEntity.getDepartment().getName())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .roles(userEntity.getRoles().stream().map(UserRoleEntity::getRole).collect(Collectors.toSet()))
                .build();
    }


    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserEntity userEntity = userRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
        return LoginResponseDTO.builder()
                .department(userEntity.getDepartment().getName())
                .email(userEntity.getEmail())
                .token(jwtService.generateToken(userEntity))
                .roles(userEntity.getRoles().stream().map(UserRoleEntity::getRole).collect(Collectors.toSet()))
                .build();
    }



}
