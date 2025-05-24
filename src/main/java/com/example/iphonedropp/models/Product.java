package com.example.iphonedropp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String firstImage;
    private int cant;

    @ElementCollection
    private List<String> imageLinks;


    //-------------------Relacion entre product y order
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerOrder_id")
    private CustomerOrder customerOrder;
    //-------------------Relacion entre product y order


    //-------------------Relacion entre category y product
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    //-------------------Relacion entre category y product



    //-----------------------------------------Metodo Constructor-----------------------------------------
    public Product() { }

    public Product(String name, String firstImage, int cant, List<String> imageLinks) {
        this.name = name;
        this.firstImage = firstImage;
        this.cant = cant;
        this.imageLinks = imageLinks;
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

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    public CustomerOrder getOrder() {
        return customerOrder;
    }

    public void setOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    //----------------------------Métodos GETTER Y SETTER------------------------------------
}
