package com.pet.service;

import com.pet.entity.User;
import com.pet.modal.request.LoginRequestDTO;
import com.pet.repository.UserRepository;
import com.pet.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtUtils;
    // private final PasswordEncoder passwordEncoder; // Dùng khi Register

    public String login(LoginRequestDTO request) {
        // 1. Xác thực thông qua AuthenticationManager
        // Nó sẽ tự động gọi UserDetailsService.loadUserByUsername (đã config tìm bằng user hoặc phone)
        // Và tự động check password encoder
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getIdentifier(),
                        request.getPassword()
                )
        );

        // 2. Nếu xác thực thành công, tìm user để generate token
        var user = userRepository.findByUsernameOrPhoneNumber(request.getIdentifier())
                .orElseThrow(); // Chắc chắn tồn tại vì bước trên đã qua

        // 3. Tạo token
        return jwtUtils.generateToken(user);
    }
}