package com.example.demo.Service;

import com.example.demo.Repository.ClientRepository;
import com.example.demo.Repository.JournalEntryRepository;
import com.example.demo.entry.Client;
import com.example.demo.entry.JournelEntry;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public void saveEntry(Client entry){
        clientRepository.save(entry);
    }

    public boolean saveNewEntry(Client entry){
        try {
            entry.setPassword(encoder.encode(entry.getPassword()));
            entry.setRoles(Arrays.asList("Client"));
            clientRepository.save(entry);
            log.info("info occur for {}",entry.getUsername());
            return true;
        }
       catch (Exception e){
             log.error("error occur for {}",entry.getUsername(),e.getMessage());
             return false;
       }
    }

    public void saveAdminEntry(Client entry){
        entry.setPassword(encoder.encode(entry.getPassword()));
        entry.setRoles(Arrays.asList("Client","ADMIN"));
        clientRepository.save(entry);
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> getById(ObjectId myId){
        return clientRepository.findById(myId);
    }


    public void deleteEntry(ObjectId myId){
        clientRepository.deleteById(myId);
    }

    public Client findByusername(String username){
       return clientRepository.findByusername(username);
    }
}
