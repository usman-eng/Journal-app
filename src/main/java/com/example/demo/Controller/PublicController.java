package com.example.demo.Controller;

import com.example.demo.Service.ClientService;
import com.example.demo.entry.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }

    @GetMapping("/get-clients")
    public List<Client> getAll(){
        return clientService.getAll();
    }

    @PostMapping("/create-client")
    public ResponseEntity<?> addClientEntry(@RequestBody Client myentry){
            boolean saved=clientService.saveNewEntry(myentry);
            if (!saved) {
                return new ResponseEntity<>("Failed to create client", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(myentry, HttpStatus.OK);

    }
}
