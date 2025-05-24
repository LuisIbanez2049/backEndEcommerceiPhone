package com.example.iphonedropp.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String img;


    //-------------------Relacion entre category y product
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    Set<Product> products = new HashSet<>();
    //-------------------Relacion entre category y product


    //-----------------------------------------Metodo Constructor-----------------------------------------
    public Category() { }

    public Category(String name, String img) {
        this.name = name;
        this.img = img;
    }
    //-----------------------------------------Metodo Constructor-----------------------------------------


    //----------------------------Métodos GETTER Y SETTER------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
//----------------------------Métodos GETTER Y SETTER------------------------------------


    public void addProduct(Product product){
        product.setCategory(this);
        products.add(product);
    }
}
