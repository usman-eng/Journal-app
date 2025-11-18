package com.example.demo.Service;

import com.example.demo.Repository.ClientRepository;
import com.example.demo.Repository.JournalEntryRepository;
import com.example.demo.entry.Client;
import com.example.demo.entry.JournelEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private ClientService clientService;


    public void saveEntry(JournelEntry entry, String username){
        try {
            Client client=clientService.findByusername(username);
            JournelEntry saved= journalEntryRepository.save(entry);
            client.getJournel().add(saved);
            clientService.saveEntry(client);
        }catch (Exception e){
            System.out.println(e);
            throw  new RuntimeException("errors appear",e);
        }

    }

    public void saveEntry(JournelEntry entry){
          journalEntryRepository.save(entry);
    }


    public List<JournelEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournelEntry> getById(ObjectId myId){
       return journalEntryRepository.findById(myId);
    }


    public boolean deleteEntry(ObjectId myId, String username){
        Client client=clientService.findByusername(username);
        boolean delet=client.getJournel().removeIf(x -> x.getId().equals(myId));
        if (delet){
            clientService.saveEntry(client);
            journalEntryRepository.deleteById(myId);
        }
       return  delet;
    }
}
