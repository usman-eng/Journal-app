package com.example.demo.Controller;

import com.example.demo.Repository.ClientRepository;
import com.example.demo.Service.ClientService;
import com.example.demo.Service.WeatherService;
import com.example.demo.api.response.WeatherResponse;
import com.example.demo.entry.Client;
import com.example.demo.entry.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private WeatherService weatherService;

    @GetMapping("id/{myid}")
    public Client getClientById(@PathVariable ObjectId myid){
        return clientService.getById(myid).orElse(null);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        clientRepository.deleteByusername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody Client newentry) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
            Client findByName = clientService.findByusername(username);
                findByName.setUsername(newentry.getUsername());
                findByName.setPassword(newentry.getPassword());
                clientService.saveNewEntry(findByName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }

    @GetMapping("city/{mycity}")
    public ResponseEntity<?> weather(@PathVariable String mycity){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse=weatherService.getWeather(mycity);
        String weather="";
        if (weatherResponse != null) {
            weather=", Weather feels like "+ weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + weather,HttpStatus.OK);
    }
}
