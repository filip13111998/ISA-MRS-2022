package rs.ac.uns.ftn.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.response.BoatDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageProfileDTO;
import rs.ac.uns.ftn.backend.service.CottageService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@RestController
@RequestMapping(value = "cottage")
public class CottageController {

    private CottageService cs;

    @Autowired
    public CottageController(CottageService cs) {
        this.cs = cs;
    }

    @GetMapping(produces = "application/json")
    public CompletableFuture<ResponseEntity<List<CottageDTO>>> getAllCottages(@RequestParam Integer pageNum , @RequestParam Integer pageSize) {
        return  cs.getAllCottages(pageNum , pageSize).thenApplyAsync(ResponseEntity::ok);
    }
    @GetMapping(value = "sort/{type}/{direction}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<CottageDTO>>> getAllCottagesSorted(@RequestParam Integer pageNum , @RequestParam Integer pageSize, @PathVariable String type , @PathVariable Boolean direction) {
        return  cs.getAllCottages(pageNum , pageSize,type,direction).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<CottageProfileDTO>> getOneCottage(@PathVariable Long id) {
        return  cs.getOneCottage(id).thenApplyAsync(ResponseEntity::ok);
    }

}
