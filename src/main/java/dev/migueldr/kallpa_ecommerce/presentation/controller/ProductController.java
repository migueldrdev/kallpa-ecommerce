package dev.migueldr.kallpa_ecommerce.presentation.controller;

import dev.migueldr.kallpa_ecommerce.business.dto.ProductDto;
import dev.migueldr.kallpa_ecommerce.business.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products") // Versionado de API (Buena práctica)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
}