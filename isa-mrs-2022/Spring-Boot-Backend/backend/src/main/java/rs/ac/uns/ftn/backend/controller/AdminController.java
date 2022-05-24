package rs.ac.uns.ftn.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.backend.dto.request.AdminLoginDTO;
import rs.ac.uns.ftn.backend.dto.response.MyUserDTO;
import rs.ac.uns.ftn.backend.service.AdminService;
import rs.ac.uns.ftn.backend.service.MyUserService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService as;


    @PostMapping(value = "/acc_active/{username}",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> checkAdmin(@PathVariable String username) {
        return this.as.checkUserActive(username).thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping(value = "/new_pass",produces = "application/json")
    public CompletableFuture<ResponseEntity<Boolean>> newPasswordAdmin(@RequestBody AdminLoginDTO dto) {
        return this.as.newAdminPassword(dto).thenApplyAsync(ResponseEntity::ok);
    }
}
