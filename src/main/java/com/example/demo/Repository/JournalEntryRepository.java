package com.example.demo.Repository;

import com.example.demo.entry.JournelEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournelEntry, ObjectId> {
}
