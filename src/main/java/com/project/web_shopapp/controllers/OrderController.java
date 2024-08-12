package com.project.web_shopapp.controllers;
import com.project.web_shopapp.dtos.CategoryDTO;
import com.project.web_shopapp.dtos.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> createOrder(
            @RequestBody @Valid OrderDTO orderDTO,
            BindingResult result
    ){
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("cerateOrder successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{user_id}") // them bien duong dan "user_id"
    // GET http://localhost:1910/api/v1/orders/4
    public ResponseEntity<?> getOrders(@Valid @PathVariable("user_id") Long userId){
        try {
            return ResponseEntity.ok("lay ra danh sach order tu user_id");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    //PUT http://localhost:1910/api/v1/orders/2
    // cong vc cua admin
    public ResponseEntity<?> updateOrder(
            @Valid @PathVariable long id,
            @Valid @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok("cap nhat thong tin 1 order");
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteOrder(@Valid @PathVariable Long id){
        //xoa mem => cap nhap truong active = false
        return ResponseEntity.ok("Order delete successfully");
    }
}
