package com.pet.controller;

import com.pet.modal.request.LoginRequestDTO;
import com.pet.modal.response.ApiResponse;
import com.pet.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequestDTO request) {
        String token = authService.login(request);

        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .message("Login thành công")
                .data(token)
                .build());
    }
}