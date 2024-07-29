package com.example.web_banhangonl.controllers;

import com.example.web_banhangonl.dtos.ProductDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(
            @Valid @RequestPart("productDTO") ProductDTO productDTO,
            BindingResult result
    ) {
        try {
            logger.info("Received request to create product");

            // Kiểm tra lỗi xác thực
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                logger.error("Validation errors: {}", errorMessages);
                return ResponseEntity.badRequest().body(errorMessages);
            }

            // Lấy file từ productDTO
            MultipartFile file = productDTO.getFile();
            if (file != null) {
                // Kiểm tra kích thước file
                if (file.getSize() > 10 * 1024 * 1024) { // kích thước > 10mb
                    logger.error("File is too large! Maximum size is 10mb");
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large! Maximum size is 10mb");
                }

                // Kiểm tra loại file
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    logger.error("File must be an image");
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("File must be an image");
                }

                // Lưu file và cập nhật thumbnail trong DTO
                String filename = storeFile(file); // Hàm này sẽ lưu file
                logger.info("File stored with filename: {}", filename);
                productDTO.setThumbnail(filename);
                // Lưu vào đối tượng product trong DB => làm sau
            }

            return ResponseEntity.ok("Product created successfully");
        } catch (Exception e) {
            logger.error("Error occurred while creating product: ", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Hàm lưu file
    private String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;

        // Đường dẫn đến thư mục mà bạn muốn lưu file
        java.nio.file.Path uploadDir = Paths.get("uploads");

        try {
            // Kiểm tra và tạo thư mục nếu nó không tồn tại
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Đường dẫn đầy đủ đến file
            java.nio.file.Path destination = uploadDir.resolve(uniqueFilename);

            // Sao chép file vào thư mục đích
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFilename;
        } catch (IOException e) {
            throw new IOException("Could not store file " + filename + ". Please try again!", e);
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("getProducts here");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductsById(@PathVariable("id") String productId) {
        return ResponseEntity.ok("Product with ID: " + productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        return ResponseEntity.ok(String.format("Product with id = %d deleted successfully", id));
    }
}
