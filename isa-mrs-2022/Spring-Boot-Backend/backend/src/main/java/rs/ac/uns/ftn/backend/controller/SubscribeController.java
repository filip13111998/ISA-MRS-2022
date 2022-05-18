package rs.ac.uns.ftn.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.request.SubscribeDTO;
import rs.ac.uns.ftn.backend.dto.response.AdventureDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.MyUserDTO;
import rs.ac.uns.ftn.backend.model.Adventure;
import rs.ac.uns.ftn.backend.model.Boat;
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.service.MyUserService;
import rs.ac.uns.ftn.backend.service.SubscribeService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/myuser/sub")
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeController {

    @Autowired
    private SubscribeService ss;

    //SUB
    @PostMapping(value = "/subCottage",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> subCottage(@RequestBody SubscribeDTO sdt) {
        return this.ss.subCottage(sdt).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/subBoat",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> subBoat(@RequestBody SubscribeDTO sdt) {
        return this.ss.subBoat(sdt).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/subAdventure",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> subAdventure(@RequestBody SubscribeDTO sdt) {
        return this.ss.subAdventure(sdt).thenApplyAsync(ResponseEntity::ok);
    }


    //UNSUB
    @PostMapping(value = "/unsubCottage",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> unsubCottage(@RequestBody SubscribeDTO sdt) {
        return this.ss.unsubCottage(sdt).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/unsubBoat",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> unsubBoat(@RequestBody SubscribeDTO sdt) {
        return this.ss.unsubBoat(sdt).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/unsubAdventure",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> unsubAdventure(@RequestBody SubscribeDTO sdt) {
        return this.ss.unsubAdventure(sdt).thenApplyAsync(ResponseEntity::ok);
    }


    //ALL SUB FOR ONE USER
    @GetMapping(value = "subCottage/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<CottageDTO>>> getAllSubCottage(@PathVariable String username) {
        return this.ss.getAllSubCottage(username).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "subBoat/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<BoatDTO>>> getAllSubBoat(@PathVariable String username) {
        return this.ss.getAllSubBoat(username).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "subAdventure/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<AdventureDTO>>> getAllSubAdventure(@PathVariable String username) {
        return this.ss.getAllSubAdventure(username).thenApplyAsync(ResponseEntity::ok);
    }


    //IS SUB USER?
    @PostMapping(value = "/isSubCottage",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> isSubCottage(@RequestBody SubscribeDTO sdt) {
        return this.ss.isSubCottage(sdt).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/isSubBoat",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> isSubBoat(@RequestBody SubscribeDTO sdt) {
        return this.ss.isSubBoat(sdt).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/isSubAdventure",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> isSubAdventure(@RequestBody SubscribeDTO sdt) {
        return this.ss.isSubAdventure(sdt).thenApplyAsync(ResponseEntity::ok);
    }
}
