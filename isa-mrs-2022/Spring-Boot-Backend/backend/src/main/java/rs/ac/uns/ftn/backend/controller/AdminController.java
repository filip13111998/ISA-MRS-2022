package rs.ac.uns.ftn.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/jed")
    public String getStr1(){
        return "jed";
    }
    @GetMapping(value = "/dva")
    public String getStr2(){
        return "Dva";
    }
}
