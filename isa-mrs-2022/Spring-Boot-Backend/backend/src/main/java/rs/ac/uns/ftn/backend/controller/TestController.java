package rs.ac.uns.ftn.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PreAuthorize("USER")
    @GetMapping(value = "/user")
    public ResponseEntity<String> getUser(){
        return new ResponseEntity<String>("USER" , HttpStatus.OK);
    }

    @PreAuthorize("ADMIN")
    @GetMapping(value = "/admin")
    public ResponseEntity<String> getAdmin(){
        return new ResponseEntity<String>("Admin" , HttpStatus.OK);
    }

}
