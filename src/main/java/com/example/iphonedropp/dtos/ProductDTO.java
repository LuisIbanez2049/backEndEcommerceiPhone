package com.example.iphonedropp.dtos;

import com.example.iphonedropp.models.Category;
import com.example.iphonedropp.models.Product;

import java.util.List;

public class ProductDTO {

    private Long id;

    private String name;
    private String firstImage;
    private int cant;
    private double price;
    private String description;
    private List<String> fileLinks;
    private String category;
    private Long categoryId;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.firstImage = product.getImageLinks().stream().findFirst().orElse(null);
        this.cant = product.getCant();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.fileLinks = product.getImageLinks();
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


    public String getCategory() {
        return category;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getFileLinks() {
        return fileLinks;
    }
}
