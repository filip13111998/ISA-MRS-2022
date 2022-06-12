package rs.ac.uns.ftn.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.request.*;
import rs.ac.uns.ftn.backend.dto.response.AdventureDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageProfileDTO;
import rs.ac.uns.ftn.backend.model.Boat;
import rs.ac.uns.ftn.backend.model.BoatPricelist;
import rs.ac.uns.ftn.backend.model.CottagePricelist;
import rs.ac.uns.ftn.backend.service.BoatService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/home/boat")
public class BoatController {
//    @Autowired
    private BoatService bs;

    @Autowired
    public BoatController(BoatService bs) {
        this.bs = bs;
    }

    @GetMapping(produces = "application/json")
    public CompletableFuture<ResponseEntity<List<BoatDTO>>> getAllBoats(@RequestParam Integer pageNum , @RequestParam Integer pageSize) {
        return  bs.getAllBoats(pageNum,pageSize).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "sort/{type}/{direction}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<BoatDTO>>> getAllBoatsSorted(@RequestParam Integer pageNum , @RequestParam Integer pageSize, @PathVariable String type , @PathVariable Boolean direction) {
        return  bs.getAllBoats(pageNum , pageSize,type,direction).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "sort/{type}/{direction}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<BoatDTO>>> getAllBoatsSorted(@RequestBody BoatSearchSortDTO bssdto, @RequestParam Integer pageNum , @RequestParam Integer pageSize, @PathVariable String type , @PathVariable Boolean direction) {
        return  bs.getAllBoatSearchSort(bssdto,pageNum , pageSize,type,direction).thenApplyAsync(ResponseEntity::ok);
    }


    @GetMapping(value = "/{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<BoatProfileDTO>> getOneBoat(@PathVariable Long id) {
        return  bs.getOneBoat(id).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/pricelist/{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<BoatPricelist>>> getBoatPricelist(@PathVariable Long id) {
        return  bs.getBoatPricelist(id).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/mark",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> addMark(@RequestBody BoatMarkDTO dto) {
        return  bs.addMark(dto).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/complaint",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> addComplaint(@RequestBody BoatComplaintDTO dto) {
        return  bs.addComplaint(dto).thenApplyAsync(ResponseEntity::ok);
    }

    @GetMapping(value = "/delete/{id}",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> delete(@PathVariable Long id) {
        return  bs.deleteCBoat(id).thenApplyAsync(ResponseEntity::ok);
    }

}
