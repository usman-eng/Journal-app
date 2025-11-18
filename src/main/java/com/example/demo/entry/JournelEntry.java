package com.example.demo.entry;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournelEntry {

    @Id
    private ObjectId id;
    private LocalDateTime date;
    @NonNull
    private String title;
    private String content;

}
