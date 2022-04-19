package rs.ac.uns.ftn.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.InstructorDTO;
import rs.ac.uns.ftn.backend.dto.response.InstructorProfileDTO;
import rs.ac.uns.ftn.backend.service.CottageService;
import rs.ac.uns.ftn.backend.service.InstructorService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "instructor")
public class InstructorController {

    private InstructorService is;

    @Autowired
    public InstructorController(InstructorService is) {
        this.is = is;
    }

    @GetMapping(produces = "application/json")
    public CompletableFuture<ResponseEntity<List<InstructorDTO>>> getAllCottages(@RequestParam Integer pageNum , @RequestParam Integer pageSize) {
        return  is.getAllInstructors(pageNum , pageSize).thenApplyAsync(ResponseEntity::ok);
    }
    @GetMapping(value = "sort/{type}/{direction}",produces = "application/json")
    public CompletableFuture<ResponseEntity<List<InstructorDTO>>> getAllCottagesSorted(@RequestParam Integer pageNum , @RequestParam Integer pageSize, @PathVariable String type , @PathVariable Boolean direction) {
        return  is.getAllInstructors(pageNum , pageSize,type,direction).thenApplyAsync(ResponseEntity::ok);
    }

//    @GetMapping(value = "/{id}",produces = "application/json")
//    public CompletableFuture<ResponseEntity<InstructorProfileDTO>> getOneCottage(@PathVariable Long id) {
//        return  is.getOneInstructor(id).thenApplyAsync(ResponseEntity::ok);
//    }

}
