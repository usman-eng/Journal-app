package com.example.demo.Repository;

import com.example.demo.entry.Client;
import com.example.demo.entry.JournelEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, ObjectId> {

     Client findByusername(String username);
     Client deleteByusername(String username);
}
