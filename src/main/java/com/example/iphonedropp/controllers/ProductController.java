package com.example.iphonedropp.controllers;

import com.example.iphonedropp.dtos.ProductDTO;
import com.example.iphonedropp.dtos.records.RecordCreateProduct;
import com.example.iphonedropp.dtos.records.RecordEditProduct;
import com.example.iphonedropp.models.Category;
import com.example.iphonedropp.models.Client;
import com.example.iphonedropp.models.ClientRol;
import com.example.iphonedropp.models.Product;
import com.example.iphonedropp.repository.CategoryRepository;
import com.example.iphonedropp.repository.ClientRepository;
import com.example.iphonedropp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CategoryRepository categoryRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductByid(Authentication authentication, @PathVariable Long id){
        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product == null) {
                return new ResponseEntity<>("No se encontro ningun producto con id: " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new ProductDTO(product), HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @PostMapping("/createProduct")
    public ResponseEntity<?> createNewProduct(Authentication authentication, @RequestBody RecordCreateProduct recordCreateProduct){
        try {
            Client client = clientRepository.findByEmail(authentication.getName());
            if (client.getClientRol().equals(ClientRol.CLIENT)) {
                return new ResponseEntity<>("No tienes permisos para realizar esta acción", HttpStatus.FORBIDDEN);
            }

            Product newProduct = new Product(recordCreateProduct.name(), recordCreateProduct.firstImage(), recordCreateProduct.cant(), recordCreateProduct.imageLinks());
            Category category = categoryRepository.findById(recordCreateProduct.categoryId()).orElse(null);
            if (category == null) {
                return new ResponseEntity<>("Categoria no encontrada", HttpStatus.NOT_FOUND);
            }
            newProduct.setCategory(category);
            category.addProduct(newProduct);
            productRepository.save(newProduct);

            return new ResponseEntity<>("Producto creado exitosamente.", HttpStatus.CREATED);

        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @PostMapping("/editProduct")
    public ResponseEntity<?> editProduct(Authentication authentication, @RequestBody RecordEditProduct recordEditProduct){
        try {
            Product product = productRepository.findById(recordEditProduct.id()).orElse(null);
            if (product == null) {
                return  new ResponseEntity<>("Producto no encontrado con id: " + recordEditProduct.id(), HttpStatus.NOT_FOUND);
            }

            product.setImageLinks(recordEditProduct.links());
            productRepository.save(product);
            return new ResponseEntity<>("Producto actualizado.", HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }
}
