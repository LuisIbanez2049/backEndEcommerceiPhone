package com.example.iphonedropp.controllers;

import com.example.iphonedropp.dtos.CategoryDTO;
import com.example.iphonedropp.dtos.records.RecordCreateCategory;
import com.example.iphonedropp.dtos.records.RecordModificarCategoria;
import com.example.iphonedropp.models.Category;
import com.example.iphonedropp.models.Client;
import com.example.iphonedropp.models.SectionCategory;
import com.example.iphonedropp.repository.CategoryRepository;
import com.example.iphonedropp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories(){
        try {
            List<CategoryDTO> categoryDTOS = categoryRepository.findAll().stream().filter(category -> category.isActive()).map(category -> new CategoryDTO(category)).collect(Collectors.toList());
            if (categoryDTOS == null) {
                return new ResponseEntity<>("Aún no hay categorias", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        try {
            CategoryDTO categoryDTO = new CategoryDTO(categoryRepository.findById(id).orElse(null));
            if (categoryDTO == null) {
                return new ResponseEntity<>("Categoria no encontrada con id: " + id, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(Authentication authentication, @RequestBody RecordCreateCategory recordCreateCategory){
        try {
            Client client = clientRepository.findByEmail(authentication.getName());
            if (client == null) {
                return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
            }

            if (recordCreateCategory.name().isEmpty()) {
                return new ResponseEntity<>("Es necesario agregar un nombre", HttpStatus.BAD_REQUEST);
            }
            if (recordCreateCategory.tipeCategory().isEmpty()) {
                return new ResponseEntity<>("Es necesario agregar el tipo de categoria", HttpStatus.BAD_REQUEST);
            }

            if (recordCreateCategory.image().isEmpty()) {
                return new ResponseEntity<>("Es necesario agregar una imagen", HttpStatus.BAD_REQUEST);
            }
            SectionCategory sectionCategory = SectionCategory.MINORISTA;
            if (recordCreateCategory.tipeCategory().equals("Mayorista")) {
                sectionCategory = SectionCategory.MAYORISTA;
            }


            Category newCategory = new Category(recordCreateCategory.name(), recordCreateCategory.image(), sectionCategory);

            categoryRepository.save(newCategory);
            return new ResponseEntity<>("Categoria creada con éxito.", HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }



    @PatchMapping("/modificarCategoria")
    public ResponseEntity<?> modifiyCategory(Authentication authentication, @RequestBody RecordModificarCategoria recordModificarCategoria){
        try {
            Category category = categoryRepository.findById(recordModificarCategoria.categoryId()).orElse(null);
            if (category == null) {
                return new ResponseEntity<>("Categoria no encontrada con id: " + recordModificarCategoria.categoryId(), HttpStatus.NOT_FOUND);
            }

            category.setName(recordModificarCategoria.name());
            category.setImg(recordModificarCategoria.img());
            category.setActive(recordModificarCategoria.isActive());
            if (recordModificarCategoria.categorySection().equals("MINORISTA")) {
                category.setSectionCategory(SectionCategory.MINORISTA);
            }
            if (recordModificarCategoria.categorySection().equals("MAYORISTA")) {
                category.setSectionCategory(SectionCategory.MAYORISTA);
            }

            categoryRepository.save(category);
            return new ResponseEntity<>("Categoria modificado con éxito.", HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }




}
