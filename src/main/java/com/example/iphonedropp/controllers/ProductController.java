package com.example.iphonedropp.controllers;

import com.example.iphonedropp.dtos.ProductDTO;
import com.example.iphonedropp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts(){
        try {
            List<ProductDTO> productDTOS = productRepository.findAll().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
            if (productDTOS == null) {
                return new ResponseEntity<>("There aren´t products yet.", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productDTOS, HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }
}
