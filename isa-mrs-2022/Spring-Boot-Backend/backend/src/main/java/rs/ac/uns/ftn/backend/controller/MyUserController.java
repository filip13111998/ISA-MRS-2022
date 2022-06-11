package rs.ac.uns.ftn.backend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.request.CottageSearchSortDTO;
import rs.ac.uns.ftn.backend.dto.request.DeleteRequestDTO;
import rs.ac.uns.ftn.backend.dto.response.*;
import rs.ac.uns.ftn.backend.model.CottagePricelist;
import rs.ac.uns.ftn.backend.service.MyUserService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/myuser/profile")
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

    @PostMapping(value = "/delete",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> deleteOneUser(@RequestBody DeleteRequestDTO del) {
        return this.mus.deleteMyUser(del).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/historyCottage/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<CottageReservationHistoryDTO>>> getAllCotagesHistoryReservation(@RequestParam Integer pageNum, @PathVariable String username ) {

        return  mus.getAllHistoryReservationCottage(username,pageNum ).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/historyBoat/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<BoatReservationHistoryDTO>>> getAllBoatsHistoryReservation(@RequestParam Integer pageNum, @PathVariable String username ) {

        return  mus.getAllHistoryReservationBoat(username,pageNum ).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/historyAdventure/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<AdventureReservationHistoryDTO>>> getAllAdventuresHistoryReservation(@RequestParam Integer pageNum, @PathVariable String username ) {

        return  mus.getAllHistoryReservationAdventure(username,pageNum ).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/notHeldCottage/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<CottageReservationHistoryDTO>>> getAllCotagesNotHeldReservation(@RequestParam Integer pageNum, @PathVariable String username ) {

        return  mus.getAllNotHeldReservationCottage(username,pageNum ).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/notHeldBoat/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<BoatReservationHistoryDTO>>> getAllBoatsNotHeldReservation(@RequestParam Integer pageNum, @PathVariable String username ) {

        return  mus.getAllNotHeldReservationBoat(username,pageNum ).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/notHeldAdventure/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<AdventureReservationHistoryDTO>>> getAllAdventuresNotHeldReservation(@RequestParam Integer pageNum, @PathVariable String username ) {

        return  mus.getAllNotHeldReservationAdventure(username,pageNum ).thenApplyAsync(ResponseEntity::ok);
    }

}
