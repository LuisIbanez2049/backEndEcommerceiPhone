package com.example.iphonedropp.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String number;
    private String password;
    private ClientRol clientRol;


    //-------------------Relacion entre client y order
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<CustomerOrder> customerOrders = new HashSet<>();
    //-------------------Relacion entre client y order




    //-----------------------------------------Metodo Constructor-----------------------------------------
    public Client() { }

    public Client(String name, String email, String number, String password) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<CustomerOrder> getOrders() {
        return customerOrders;
    }

    public void setOrders(Set<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientRol getClientRol() {
        return clientRol;
    }

    public void setClientRol(ClientRol clientRol) {
        this.clientRol = clientRol;
    }
    //----------------------------Métodos GETTER Y SETTER------------------------------------


    public void addorder(CustomerOrder customerOrder){
        customerOrder.setClient(this);
        customerOrders.add(customerOrder);
    }
}
