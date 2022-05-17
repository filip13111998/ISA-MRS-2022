package rs.ac.uns.ftn.backend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.request.DeleteAdventureReservationDTO;
import rs.ac.uns.ftn.backend.dto.request.DeleteCottageReservationDTO;
import rs.ac.uns.ftn.backend.dto.request.SaveAdventureReservationDTO;
import rs.ac.uns.ftn.backend.dto.request.SaveCottageReservationDTO;
import rs.ac.uns.ftn.backend.dto.response.AdventureReservationCalendarDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageReservationCalendarDTO;
import rs.ac.uns.ftn.backend.service.AdventureReservationService;
import rs.ac.uns.ftn.backend.service.CottageReservationService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/myuser/reservationAdventure")
@AllArgsConstructor
@NoArgsConstructor
public class AdventureReservationController {
    @Autowired
    private AdventureReservationService ars;

    @PostMapping(produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> saveAdventureReservation(@RequestBody SaveAdventureReservationDTO acr) {
        return  ars.saveReservation(acr).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<AdventureReservationCalendarDTO>> getAllAdventureReservations(@PathVariable Long id) {
        return  ars.getAdventureReservations(id).thenApplyAsync(ResponseEntity::ok);
    }

    @DeleteMapping(produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> deleteAdventureReservation(@RequestBody DeleteAdventureReservationDTO acr) {
        return  ars.deleteReservation(acr).thenApplyAsync(ResponseEntity::ok);
    }
}
