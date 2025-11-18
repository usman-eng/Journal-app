package com.example.demo.Controller;

import com.example.demo.Service.ClientService;
import com.example.demo.entry.Client;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Client addClientEntry(@RequestBody Client myentry){
        clientService.saveNewEntry(myentry);
        return myentry;
    }
}
