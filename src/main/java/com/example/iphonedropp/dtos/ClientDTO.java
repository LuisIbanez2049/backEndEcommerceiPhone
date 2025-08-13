package com.example.iphonedropp.dtos;

import com.example.iphonedropp.models.Client;

public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String number;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.number = client.getNumber();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }
}
