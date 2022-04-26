package rs.ac.uns.ftn.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.response.AdventureDTO;
import rs.ac.uns.ftn.backend.dto.response.AdventureProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageProfileDTO;
//import rs.ac.uns.ftn.backend.model.Adventure;
import rs.ac.uns.ftn.backend.service.AdventureService;
import rs.ac.uns.ftn.backend.service.CottageService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "home/adventure")
public class AdventureController {

    private AdventureService as;

    @Autowired
    public AdventureController(AdventureService as) {
        this.as = as;
    }

    @GetMapping(produces = "application/json")
    public CompletableFuture<ResponseEntity<List<AdventureDTO>>> getAllAdventures(@RequestParam Integer pageNum , @RequestParam Integer pageSize) {
        return  as.getAllAdventures(pageNum , pageSize).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "sort/{type}/{direction}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<AdventureDTO>>> getAllAdventuresSorted(@RequestParam Integer pageNum , @RequestParam Integer pageSize, @PathVariable String type , @PathVariable Boolean direction) {
        return  as.getAllAdventures(pageNum , pageSize,type,direction).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<AdventureProfileDTO>> getOneAdventure(@PathVariable Long id) {
        return  as.getOneAdventure(id).thenApplyAsync(ResponseEntity::ok);
    }

}
