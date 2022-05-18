package rs.ac.uns.ftn.backend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.request.DeleteCottageReservationDTO;
import rs.ac.uns.ftn.backend.dto.request.SaveCottageReservationDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageReservationCalendarDTO;
import rs.ac.uns.ftn.backend.service.CottageReservationService;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping(value = "/myuser/reservationCottage")
@AllArgsConstructor
@NoArgsConstructor
public class CottageReservationController {

    @Autowired
    private CottageReservationService crs;

    @PostMapping(produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> saveCottageReservation(@RequestBody SaveCottageReservationDTO scr) {
        return  crs.saveReservation(scr).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<CottageReservationCalendarDTO>> getAllCottageReservations(@PathVariable Long id) {
        return  crs.getCottageReservations(id).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/delete",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> deleteCottageReservation(@RequestBody DeleteCottageReservationDTO dcr) {
        return  crs.deleteReservation(dcr).thenApplyAsync(ResponseEntity::ok);
    }
}
