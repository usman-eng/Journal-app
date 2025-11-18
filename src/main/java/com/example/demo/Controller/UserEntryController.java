package com.example.demo.Controller;

import com.example.demo.Service.UserEntryService;
import com.example.demo.entry.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;

   @GetMapping
    public List<UserEntry> userEntryList(){
        return userEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<UserEntry> saveAll(@RequestBody UserEntry mydata){
       try {
           userEntryService.saveAll(mydata);
           return new ResponseEntity<>(mydata,HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

    }

    @GetMapping("id/{myid}")
    public ResponseEntity<UserEntry> findById(@PathVariable ObjectId myid){
        Optional<UserEntry> userEntry=userEntryService.find(myid);
        if(userEntry.isPresent()){
            return new ResponseEntity<>(userEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}")
    public Boolean delete(@PathVariable ObjectId myid){
         userEntryService.delete(myid);
        return true;
    }

    @PutMapping("id/{myid}")
    public UserEntry saveAll(@PathVariable ObjectId myid,@RequestBody UserEntry mydata){
        UserEntry old= userEntryService.find(myid).orElse(null);
        if(old != null){
            old.setName(mydata.getName() !=null && !mydata.getName().equals(" ") ? mydata.getName() : old.getName());
            old.setEmail(mydata.getEmail() !=null && !mydata.getEmail().equals(" ") ? mydata.getEmail() : old.getEmail());
        }

        userEntryService.saveAll(old);
        return mydata;
    }


}
