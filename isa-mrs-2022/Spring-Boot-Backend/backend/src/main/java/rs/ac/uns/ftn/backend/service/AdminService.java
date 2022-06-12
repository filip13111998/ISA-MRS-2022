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
import rs.ac.uns.ftn.backend.dto.response.AdminProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.LoyalityDTO;
import rs.ac.uns.ftn.backend.dto.response.MyUserDTO;
import rs.ac.uns.ftn.backend.model.Administrator;
import rs.ac.uns.ftn.backend.model.LoyalityProgram;
import rs.ac.uns.ftn.backend.model.MyUser;
import rs.ac.uns.ftn.backend.model.Role;
import rs.ac.uns.ftn.backend.repository.AdminRepository;
import rs.ac.uns.ftn.backend.repository.MyUserRepository;
import rs.ac.uns.ftn.backend.repository.RoleRepository;

import java.util.List;
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

    @Autowired
    private RoleRepository rr;

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

    @Async
    public CompletableFuture<LoyalityDTO> getLoyalityProgram() {

        log.info("GET LOYALITY PROGRAM");

        LoyalityDTO ld = new LoyalityDTO(LoyalityProgram.appProfit , LoyalityProgram.silverPercent,LoyalityProgram.goldPercent
                                        , LoyalityProgram.silverPoints,LoyalityProgram.goldPoints);



        return CompletableFuture.completedFuture(ld);

    }

    @Async
    public CompletableFuture<Boolean> saveLoyalityProgram(LoyalityDTO nl) {

        log.info("SAVE LOYALITY PROGRAM");

        LoyalityProgram.appProfit = nl.appProfit;
        LoyalityProgram.goldPoints = nl.goldPoints;
        LoyalityProgram.silverPoints = nl.silverPoints;
        LoyalityProgram.goldPercent = nl.goldPercent;
        LoyalityProgram.silverPercent = nl.silverPercent;

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<AdminProfileDTO> getOneAdmin(String username) {

        log.info("GET ONE ADMIN WITH USERNAME: " + username);

        Administrator adm = ar.findByUsername(username);

        AdminProfileDTO adto = new AdminProfileDTO(
                adm.getId(),adm.getEmail(),adm.getUsername(),adm.getPassword(),adm.getFirstName(),adm.getLastName()
        );

        return CompletableFuture.completedFuture(adto);

    }

    @Async
    public CompletableFuture<Boolean> saveAdmin(AdminProfileDTO adto) {

        log.info("SAVE ADMIN WITH USERNAME: " + adto.getUsername());

        Administrator adm = new Administrator();

        List<Role> list_role = rr.findByName("ROLE_ADMIN");


        adm.setFirstName(adto.getFirstName());
        adm.setLastName(adto.getLastName());
        adm.setEmail(adto.getEmail());
        adm.setUsername(adto.getUsername());
        adm.setPassword(encoder.encode(adto.getPassword()));
        adm.setActivateAccount(false);
        adm.setDeactivate(true);
        adm.setEnabled(true);
        adm.setRoles(list_role);

        ar.save(adm);

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> updateAdmin(AdminProfileDTO adto) {

        log.info("UPDATE ADMIN WITH USERNAME: " + adto.getUsername());

        Administrator a = ar.findByUsername(adto.getUsername());
        a.setFirstName(adto.getFirstName());
        a.setLastName(adto.getLastName());
        a.setEmail(adto.getEmail());
        a.setPassword(encoder.encode(adto.getPassword()));

        ar.save(a);

        return CompletableFuture.completedFuture(true);

    }

}
