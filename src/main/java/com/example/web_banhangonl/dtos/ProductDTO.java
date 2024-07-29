package com.example.web_banhangonl.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotBlank(message = "Tiêu đề là bắt buộc")
    @Size(min = 3 , max = 200 , message = "Tiêu đề phải dài từ 3 đến 200 ký tự" )
    private String name;

    @Min(value =0 , message = "giá phải lớn hơn hoặc bằng 0")
    @Max(value = 100000000, message = "giá phải nhỏ hơn hoặc bằng 10.000.000")
    private float price;
    private String thumbnail;
    private String description;
    @JsonProperty("category_id")
    private String categoryId;
    private MultipartFile file;
}
