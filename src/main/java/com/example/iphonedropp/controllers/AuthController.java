package com.example.iphonedropp.controllers;


import com.example.iphonedropp.dtos.ClientDTO;
import com.example.iphonedropp.dtos.records.RecordLogin;
import com.example.iphonedropp.dtos.records.RecordRegister;
import com.example.iphonedropp.models.Client;
import com.example.iphonedropp.repository.ClientRepository;
import com.example.iphonedropp.servicesSecurity.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RecordLogin recordLogin){
        try {
            if (recordLogin.email().isBlank()) {
                return new ResponseEntity<>("Ingrese un mail.", HttpStatus.BAD_REQUEST);
            }
            if (recordLogin.password().isBlank()) {
                return new ResponseEntity<>("Ingrese contraseña.", HttpStatus.BAD_REQUEST);
            }
            Client client = clientRepository.findByEmail(recordLogin.email());
            if (client == null) {
                return new ResponseEntity<>("Email no registrado.", HttpStatus.NOT_FOUND);
            }

            System.out.println("Login attempt for: " + recordLogin.email());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(recordLogin.email(), recordLogin.password()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(recordLogin.email());
            final String jwt = jwtUtilService.generateToken(userDetails);
            System.out.println("JWT GENERATED: " + jwt);
            return ResponseEntity.ok(jwt);

        } catch (Exception e) {
            e.printStackTrace(); // Muestra cualquier excepción que ocurra.
            // Retorna un error si la autenticación falla.
            return new ResponseEntity<>("Contraseña Incorrecta.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RecordRegister recordRegister){

        if (recordRegister.name().isBlank()) {
            return new ResponseEntity<>("First name can not be empty.", HttpStatus.BAD_REQUEST);
        }
        if (recordRegister.name().length() < 2) { return new ResponseEntity<>("Invalid first name. Please provide at least 2 characters.", HttpStatus.BAD_REQUEST);}


        if (recordRegister.email().isBlank()) {
            return new ResponseEntity<>("Email can not be empty.", HttpStatus.BAD_REQUEST);
        }
        if (!recordRegister.email().contains("@")) { return new ResponseEntity<>("Invalid email. It must contain an '@' character.", HttpStatus.BAD_REQUEST); }
        if (!recordRegister.email().contains(".com") && !recordRegister.email().contains(".net") && !recordRegister.email().contains(".org") &&
                !recordRegister.email().contains(".co") && !recordRegister.email().contains(".info")) {
            return new ResponseEntity<>("Invalid email. Please enter a valid domain extension since '.com', '.net', '.org', '.co' or '.info'.", HttpStatus.BAD_REQUEST); }
        if (recordRegister.email().contains("@.")) { return new ResponseEntity<>("Invalid email. Please provide a valid domain since 'gmail', 'yahoo', etc., " +
                "between the characters '@' and the character '.'", HttpStatus.BAD_REQUEST); }
        if (clientRepository.findByEmail(recordRegister.email()) != null) {
            return new ResponseEntity<>("Email not available. Already in use.", HttpStatus.BAD_REQUEST);
        }
        if (recordRegister.password().isBlank()) {
            return new ResponseEntity<>("Password can not be empty.", HttpStatus.BAD_REQUEST);
        }
        if (recordRegister.password().length() < 8) {
            return new ResponseEntity<>("Password must be at least 8 characters long.", HttpStatus.BAD_REQUEST);
        }

        String encodePassword = passwordEncoder.encode(recordRegister.password());
        Client newClient = new Client(recordRegister.name(), recordRegister.email(), recordRegister.number(), encodePassword);
        clientRepository.save(newClient);
        return new ResponseEntity<>("USER REGISTERED SUCCESSFULLY", HttpStatus.OK);

    }

    @GetMapping("/current")
    public ResponseEntity<?> getAuthenticatedClient(Authentication authentication){
        try {
            Client client = clientRepository.findByEmail(authentication.getName());
            if (client == null) {
                return new ResponseEntity<>("Client not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new ClientDTO(client), HttpStatus.OK);
        } catch (Exception e) { return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); }
    }
}
