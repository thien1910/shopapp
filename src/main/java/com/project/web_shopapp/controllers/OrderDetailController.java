package com.project.web_shopapp.controllers;
import com.project.web_shopapp.dtos.CategoryDTO;
import com.project.web_shopapp.dtos.OrderDTO;
import com.project.web_shopapp.dtos.OrderdetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("${api.prefix}/orders_details")
public class OrderDetailController {
    // them moi 1 order detail
    @PostMapping
    public ResponseEntity<?> createOrderDetail(
            @Valid @RequestBody OrderdetailDTO orderdetailDTO){
        return ResponseEntity.ok("create Orderdetail here");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(
            @Valid @PathVariable ("id") Long id) {
        return ResponseEntity.ok("getOrderDetail with id = " +id );
    }
    // lay ra danh sach cac order_details cua 1 order nao do
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(
            @Valid @PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok("getOrderDetails with orderId = " +orderId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable("id") Long id,
            @RequestBody OrderdetailDTO newOrderDetailData){
        return ResponseEntity.ok("updateOrderDetail with id = " +id
                +",newOrderDetailData" +newOrderDetailData);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(
            @Valid @PathVariable("id") Long id ) {
        return ResponseEntity.noContent().build();
    }
}
