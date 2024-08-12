package com.project.web_shopapp.services;

import com.project.web_shopapp.dtos.ProductDTO;
import com.project.web_shopapp.dtos.ProductImageDTO;
import com.project.web_shopapp.exceptions.DataNotFoundException;
import com.project.web_shopapp.models.Product;
import com.project.web_shopapp.models.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.PrimitiveIterator;

public interface IProductService {
    Product createProduct(ProductDTO productDTO) throws Exception;
    Product getProductById(long id) throws Exception;
    Page<Product> getAllProducts(PageRequest pageRequest);
    Product updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
    ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception;

}
