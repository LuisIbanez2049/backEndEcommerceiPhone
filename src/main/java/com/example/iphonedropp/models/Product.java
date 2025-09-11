package com.example.iphonedropp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int stock;
    private double price;

    @Lob
    @Column(columnDefinition = "TEXT") // Para MySQL/PostgreSQL
    private String description;

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

    public Product(String name, int stock, List<String> imageLinks) {
        this.name = name;
        this.stock = stock;
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


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //----------------------------Métodos GETTER Y SETTER------------------------------------

    public void addImg(String img){
        imageLinks.add(img);
    }
}
