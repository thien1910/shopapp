package com.project.web_shopapp.controllers;

import com.project.web_shopapp.dtos.CategoryDTO;
import com.project.web_shopapp.models.Category;
import com.project.web_shopapp.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("${api.prefix}/categories")
//@Validated
// dependency injection
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("")
    // nếu tham số chuyền vào là 1 object thì sao ? => Data transfer Object = Request Object
    public ResponseEntity<?> createCategories(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result){
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("Insert category successfully");
    }
    // hiển thị tất cả categories
    @GetMapping("") //http://localhost:1910/api/v1/categories?page=1&limt=10
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam("page")   int page,
            @RequestParam("limit")  int limit
    ) {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategories(
            @PathVariable Long id,
           @Valid @RequestBody CategoryDTO categoryDTO
    ){
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("update category successfully");
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteCategories(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("deleteCategory with id = " +id+ "successfully");
    }
}
