package rs.ac.uns.ftn.backend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.request.DeleteBoatReservationDTO;
import rs.ac.uns.ftn.backend.dto.request.DeleteCottageReservationDTO;
import rs.ac.uns.ftn.backend.dto.request.SaveBoatReservationDTO;
import rs.ac.uns.ftn.backend.dto.request.SaveCottageReservationDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatReservationCalendarDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageReservationCalendarDTO;
import rs.ac.uns.ftn.backend.service.BoatReservationService;
import rs.ac.uns.ftn.backend.service.CottageReservationService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/myuser/reservationBoat")
@AllArgsConstructor
@NoArgsConstructor
public class BoatReservationController {

    @Autowired
    private BoatReservationService brs;

    @PostMapping(produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> saveBoatReservation(@RequestBody SaveBoatReservationDTO sbr) {
        return  brs.saveReservation(sbr).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<BoatReservationCalendarDTO>> getAllBoatReservations(@PathVariable Long id) {
        return  brs.getBoatReservations(id).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/delete",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> deleteBoatReservation(@RequestBody DeleteBoatReservationDTO dbr) {
        System.out.println(dbr + "GSAGSA");
        return  brs.deleteReservation(dbr).thenApplyAsync(ResponseEntity::ok);
    }
}
