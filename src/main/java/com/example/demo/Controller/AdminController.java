package com.example.demo.Controller;

import com.example.demo.Service.ClientService;
import com.example.demo.entry.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers(){
        List<Client> all=clientService.getAll();
        if (all != null && !all.isEmpty()){
            return  new ResponseEntity<>(all, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/create-admin")
    public  ResponseEntity<?> createAdmin(@RequestBody Client mydata){
        try {
            clientService.saveAdminEntry(mydata);
            return new ResponseEntity<>(mydata,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
