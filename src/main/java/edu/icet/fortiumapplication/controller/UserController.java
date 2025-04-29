package edu.icet.fortiumapplication.controller;


import edu.icet.fortiumapplication.dto.RegisterUserRequestDTO;
import edu.icet.fortiumapplication.dto.UserDTO;
import edu.icet.fortiumapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;


    @GetMapping("/test")
    public String test(){
        return "test pass";
    }


    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid RegisterUserRequestDTO requestDTO){
        return ResponseEntity.ok(userService.create(requestDTO));
    }



}
