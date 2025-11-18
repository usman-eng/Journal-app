package com.example.demo.Controller;

import com.example.demo.Service.ClientService;
import com.example.demo.Service.JournalEntryService;
import com.example.demo.entry.Client;
import com.example.demo.entry.JournelEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/journal")
public class JournalEntryController_V2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private  ClientService clientService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Client client=clientService.findByusername(username);
        List<JournelEntry> journelEntries= client.getJournel();
        if (journelEntries != null && !journelEntries.isEmpty()){
            return new ResponseEntity<>(journelEntries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addJournelEntry(@RequestBody JournelEntry myentry){
      Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
      String username=authentication.getName();
        try {
          myentry.setDate(LocalDateTime.now());
          journalEntryService.saveEntry(myentry,username);
          return new ResponseEntity<>(myentry,HttpStatus.OK);
      }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }

    }

    @GetMapping("id/{myid}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myid){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Client client=clientService.findByusername(username);
        List<JournelEntry> journelEntries= client.getJournel().stream().filter(x ->x.getId().equals(myid)).collect(Collectors.toList());
       if(!journelEntries.isEmpty()){
           Optional<JournelEntry> journelEntry=journalEntryService.getById(myid);
           if(journelEntry.isPresent()){
               return  new ResponseEntity<>(journelEntry.get(),HttpStatus.OK);
           }
       }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> delete(@PathVariable ObjectId myid){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
         boolean data=journalEntryService.deleteEntry(myid,username);
         if (data){
             return new ResponseEntity<>(HttpStatus.OK);
         }
         return  new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> updateJournelEntry(@PathVariable ObjectId myid,@RequestBody JournelEntry newentry){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Client client=clientService.findByusername(username);
        List<JournelEntry> journelEntries= client.getJournel().stream().filter(x ->x.getId().equals(myid)).collect(Collectors.toList());
        if(!journelEntries.isEmpty()){
            Optional<JournelEntry> journelEntry=journalEntryService.getById(myid);
            if(journelEntry.isPresent()){
                JournelEntry oldentry=journelEntry.get();
                oldentry.setTitle(newentry.getTitle() != null && !newentry.getTitle().equals(" ") ? newentry.getTitle() : oldentry.getTitle());
                oldentry.setContent(newentry.getContent() != null && !newentry.getContent().equals(" ") ? newentry.getContent() : oldentry.getContent());
                journalEntryService.saveEntry(oldentry);
                return new ResponseEntity<>(oldentry,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
