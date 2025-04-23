package com.DB.DBConnect.controller;

import com.DB.DBConnect.entity.JournalEntry;
import com.DB.DBConnect.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/allEntry")
    public List<JournalEntry> getAllEntry(){
        return journalEntryService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
       try{
           myEntry.setDate(LocalDateTime.now());
           journalEntryService.savaEntry(myEntry);
           return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
       }catch(Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId){
        Optional<JournalEntry>journalEntry  = journalEntryService.findById(myId);
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("del/{myId}")
    public ResponseEntity<?> delById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{Id}")
    public ResponseEntity<JournalEntry> updateBy(@PathVariable ObjectId Id, @RequestBody JournalEntry newEntry){
         JournalEntry old = journalEntryService.findById(Id).orElse(null);
         if(old != null){
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
                journalEntryService.savaEntry(old);
                return new ResponseEntity<>(old,HttpStatus.CREATED);
         }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
