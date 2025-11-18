package com.example.demo.Service;

import com.example.demo.Repository.UserEntryRepository;
import com.example.demo.entry.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;


    public List<UserEntry> getAll(){
        return userEntryRepository.findAll();
    }

    public UserEntry saveAll(UserEntry mydata){
        userEntryRepository.save(mydata);
        return mydata;
    }

    public Optional<UserEntry> find(ObjectId myid){
        return userEntryRepository.findById(myid);
    }

    public void delete(ObjectId myid){
        userEntryRepository.deleteById(myid);
    }

}
