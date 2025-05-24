package com.example.iphonedropp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime localDateTime;
    private String numberOrder;


    //-------------------Relacion entre client y order
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    //-------------------Relacion entre client y order


    //-------------------Relacion entre order y product
    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.EAGER)
    Set<Product> products = new HashSet<>();
    //-------------------Relacion entre order y product




    //-----------------------------------------Metodo Constructor-----------------------------------------
    public CustomerOrder() { }

    public CustomerOrder(LocalDateTime localDateTime, String numberOrder) {
        this.localDateTime = localDateTime;
        this.numberOrder = numberOrder;
    }
    //-----------------------------------------Metodo Constructor-----------------------------------------


    //----------------------------Métodos GETTER Y SETTER------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    //----------------------------Métodos GETTER Y SETTER------------------------------------

    public void addProduct(Product product){
        product.setOrder(this);
        products.add(product);
    }
}
