package com.example.iphonedropp.controllers;

import com.example.iphonedropp.dtos.ClientDTO;
import com.example.iphonedropp.models.Client;
import com.example.iphonedropp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllClients(Authentication authentication){
        try {
            List<ClientDTO> clientDTOs = clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
            return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findClientById(Authentication authentication, @PathVariable Long id){
        try {
            Client client = clientRepository.findById(id).orElse(null);
            if (client == null) {
                return new ResponseEntity<>("Usuario no encontrado.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
        }  catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }
}
