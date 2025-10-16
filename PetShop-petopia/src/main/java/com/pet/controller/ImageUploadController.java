package com.pet.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:5173")
// Cho phép frontend React truy cập
public class ImageUploadController {

    @Value("${upload.path:uploads}")
    private String uploadDir;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 1️⃣ Tạo thư mục nếu chưa tồn tại
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 2️⃣ Đặt lại tên file an toàn
            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path targetLocation = uploadPath.resolve(fileName);

            // 3️⃣ Lưu file
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // 4️⃣ Trả về URL để frontend hiển thị
            String fileUrl = "/uploads/" + fileName;
            return ResponseEntity.ok().body(new UploadResponse(fileUrl));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Không thể upload file: " + e.getMessage());
        }
    }

    // ✅ class phụ trợ để trả JSON
    private record UploadResponse(String url) {}
}
