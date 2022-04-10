package rs.ac.uns.ftn.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.backend.repository.CottageRepository;
import rs.ac.uns.ftn.backend.service.CottageService;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    CottageService cs;

    @Autowired
    public HelloController(CottageService cs2){
        cs = cs2;
    }

    @GetMapping(value = "helmet")
    public ResponseEntity<String> getMet(){
        return new ResponseEntity<>("Filip Vasic" , HttpStatus.OK);
    }

    @GetMapping(value = "avgCot")
    public ResponseEntity<String> getAvg(){
        double avg = cs.averageCottageMarks();
        return new ResponseEntity<>("AVERAGE IS: " + avg , HttpStatus.OK);
    }
}
