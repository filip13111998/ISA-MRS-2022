package rs.ac.uns.ftn.backend.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.response.CottageProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.MyUserDTO;
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.model.MyUser;
import rs.ac.uns.ftn.backend.repository.MyUserRepository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class MyUserService {

    @Autowired
    private MyUserRepository mur;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Async
    public CompletableFuture<MyUserDTO> getOneMyUser(String username) {

        log.info("GET ONE USER WITH EMAIL: " + username);

        MyUser mu = mur.findByUsername(username);

        MyUserDTO mudto = new MyUserDTO(
                mu.getId(),mu.getEmail(),mu.getUsername(),mu.getPassword(),mu.getFirstName(),mu.getLastName(),mu.getRoles()
        );

        return CompletableFuture.completedFuture(mudto);

    }

    @Async
    public CompletableFuture<Boolean> editMyUser(MyUserDTO mud) {

        log.info("GET ONE USER WITH EMAIL: " + mud.getUsername());

        MyUser mu = mur.findByUsername(mud.getUsername());

        mu.setFirstName(mud.getFirstName());
        mu.setLastName(mud.getLastName());
        mu.setPassword(passwordEncoder.encode(mud.getPassword()));
        mu.setEmail(mud.getEmail());

        mur.save(mu);

        return CompletableFuture.completedFuture(true);

    }
}
