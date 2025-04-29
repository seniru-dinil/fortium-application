package edu.icet.fortiumapplication.controller;


import edu.icet.fortiumapplication.dto.LoginRequestDTO;
import edu.icet.fortiumapplication.dto.LoginResponseDTO;
import edu.icet.fortiumapplication.dto.RegisterUserRequestDTO;
import edu.icet.fortiumapplication.dto.UserDTO;
import edu.icet.fortiumapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid RegisterUserRequestDTO requestDTO){
        return ResponseEntity.ok(userService.create(requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> fetchById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.fetchById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> fetchAll(){
        return ResponseEntity.ok(userService.fetchAll());
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<UserDTO>> fetchAllByDepartment(@PathVariable String department){
        return ResponseEntity.ok(userService.fetchAllByDepartment(department));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDTO>> fetchAllByKeyword(@PathVariable String keyword){
        return ResponseEntity.ok(userService.fetchAll(keyword));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO){
        return ResponseEntity.ok(userService.login(requestDTO));
    }

}
