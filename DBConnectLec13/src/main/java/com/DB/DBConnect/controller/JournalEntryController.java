package com.DB.DBConnect.controller;

import com.DB.DBConnect.entity.JournalEntry;
import com.DB.DBConnect.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/allEntry")
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping("/create")
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.savaEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("del/{myId}")
    public boolean delById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{Id}")
    public JournalEntry updateBy(@PathVariable ObjectId Id, @RequestBody JournalEntry newEntry){
         JournalEntry old = journalEntryService.findById(Id).orElse(null);
         if(old != null){
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
         }
         journalEntryService.savaEntry(old);
         return old;
    }
}
