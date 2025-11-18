package com.example.demo.entry;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document
@Data
public class UserEntry {
    @Id
    private ObjectId id;
    private String name;
    private String email;

}
