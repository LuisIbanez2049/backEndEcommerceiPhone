package com.example.iphonedropp.dtos;

import com.example.iphonedropp.models.Category;
import com.example.iphonedropp.models.Product;

import java.util.List;

public class ProductDTO {

    private Long id;

    private String name;
    private String firstImage;
    private int cant;
    private List<String> imageLinks;
    private String category;
    private Long categoryId;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.firstImage = product.getFirstImage();
        this.cant = product.getCant();
        this.imageLinks = product.getImageLinks();
        this.category = product.getCategory().getName();
        this.categoryId = product.getCategory().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public int getCant() {
        return cant;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public String getCategory() {
        return category;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
