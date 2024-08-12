package com.project.web_shopapp.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data //to String
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDTO {
    @NotEmpty(message = "Category's name cannot be empty")
    private String name;
}
