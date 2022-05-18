package rs.ac.uns.ftn.backend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.request.SubscribeDTO;
import rs.ac.uns.ftn.backend.model.AdventureAction;
import rs.ac.uns.ftn.backend.model.BoatAction;
import rs.ac.uns.ftn.backend.model.CottageAction;
import rs.ac.uns.ftn.backend.service.MyUserActionService;
import rs.ac.uns.ftn.backend.service.SubscribeService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/myuser/actions")
@AllArgsConstructor
@NoArgsConstructor
public class MyUserActionController {

    @Autowired
    private MyUserActionService mas;

    //GET ALL COTTAGE ACTION
    @GetMapping(value = "/getAllCottageAction/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<CottageAction>>> getAllCottageAction(@PathVariable String username) {
        return this.mas.getAllCottageAction(username).thenApplyAsync(ResponseEntity::ok);
    }

    //GET ALL COTTAGE ACTION
    @GetMapping(value = "/getAllBoatAction/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<BoatAction>>> getAllBoatAction(@PathVariable String username) {
        return this.mas.getAllBoatAction(username).thenApplyAsync(ResponseEntity::ok);
    }

    //GET ALL COTTAGE ACTION
    @GetMapping(value = "/getAllAdventureAction/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<AdventureAction>>> getAllAdventureAction(@PathVariable String username) {
        return this.mas.getAllAdventureAction(username).thenApplyAsync(ResponseEntity::ok);
    }

}
