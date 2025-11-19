package com.pet.modal.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "Username hoặc số điện thoại không được trống")
    private String identifier;

    @NotBlank(message = "Mật khẩu không được trống")
    private String password;
}