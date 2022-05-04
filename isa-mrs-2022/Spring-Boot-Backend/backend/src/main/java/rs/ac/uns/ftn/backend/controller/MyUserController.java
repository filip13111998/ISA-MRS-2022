package rs.ac.uns.ftn.backend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.MyUserDTO;
import rs.ac.uns.ftn.backend.service.MyUserService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/home/myuser")
@AllArgsConstructor
@NoArgsConstructor
public class MyUserController {

    @Autowired
    private MyUserService mus;

    @GetMapping(value = "/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<MyUserDTO>> getOneUser(@PathVariable String username) {
        return this.mus.getOneMyUser(username).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> editOneUser(@RequestBody MyUserDTO mud) {
        return this.mus.editMyUser(mud).thenApplyAsync(ResponseEntity::ok);
    }

}
