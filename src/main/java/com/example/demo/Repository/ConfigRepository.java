package com.example.demo.Repository;

import com.example.demo.entry.ConfigEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<ConfigEntry, ObjectId> {
}
