package com.RestAPI.journalApp.controller;

import com.RestAPI.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("/abc")
    public List<JournalEntry> getAll(){
          return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getById(@PathVariable Long myId){
           return journalEntries.get(myId);
    }

    @DeleteMapping("del/{myId}")
    public JournalEntry delById(@PathVariable Long myId){
           return journalEntries.remove(myId);
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateBy(@PathVariable Long myId, @RequestBody JournalEntry myEntry){
        return journalEntries.put(myId,myEntry);
    }
}
