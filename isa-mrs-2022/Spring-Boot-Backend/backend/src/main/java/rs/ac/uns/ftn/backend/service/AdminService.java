package rs.ac.uns.ftn.backend.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.AdminLoginDTO;
import rs.ac.uns.ftn.backend.dto.response.MyUserDTO;
import rs.ac.uns.ftn.backend.model.Administrator;
import rs.ac.uns.ftn.backend.model.MyUser;
import rs.ac.uns.ftn.backend.repository.AdminRepository;
import rs.ac.uns.ftn.backend.repository.MyUserRepository;

import java.util.concurrent.CompletableFuture;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class AdminService {

    @Autowired
    private AdminRepository ar;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Async
    public CompletableFuture<Boolean> checkUserActive(String username) {

        log.info("CHECK ADMIN ACTIVE : " + username);

        Administrator adm = ar.findByUsername(username);

        return CompletableFuture.completedFuture(adm.getActivateAccount());

    }

    @Async
    public CompletableFuture<Boolean> newAdminPassword(AdminLoginDTO aldto) {

        log.info("CHAGE ADMIN PASSWORD FIRST LOGIN : " + aldto.getUsername());

        Administrator adm = ar.findByUsername(aldto.getUsername());

        adm.setActivateAccount(true);

        adm.setPassword(encoder.encode(aldto.getPassword()));

        ar.save(adm);

        return CompletableFuture.completedFuture(true);

    }
}
