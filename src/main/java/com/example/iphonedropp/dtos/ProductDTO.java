package com.example.iphonedropp.dtos;

import com.example.iphonedropp.models.Product;

import java.util.List;

public class ProductDTO {

    private Long id;

    private String name;
    private String firstImage;
    private int cant;
    private List<String> imageLinks;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.firstImage = product.getFirstImage();
        this.cant = product.getCant();
        this.imageLinks = product.getImageLinks();
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
}
