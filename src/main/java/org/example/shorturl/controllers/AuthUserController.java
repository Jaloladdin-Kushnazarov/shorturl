package org.example.shorturl.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.shorturl.dtos.auth.AuthUserCreateDto;
import org.example.shorturl.dtos.auth.GenerateTokenRequest;
import org.example.shorturl.sevises.AuthUserServise;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserServise  authUserService;


    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@Valid @RequestBody GenerateTokenRequest request) {
        return ResponseEntity.ok(authUserService.generateToken(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody AuthUserCreateDto dto) {
        return ResponseEntity.status(201).body(authUserService.register(dto));
    }


    // TODO: 28/04/23 add activate path mapping
    @GetMapping("/activate/{code}")
    ResponseEntity<String> activateAccount(@PathVariable String code) {
        return ResponseEntity.ok(authUserService.activateAccount(code));
    }


    // TODO:   activation code ni qayta jo'natadigan api go'shish kerak (email)



}