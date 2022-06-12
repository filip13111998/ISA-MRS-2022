package rs.ac.uns.ftn.backend.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.response.AdminProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatDTO;
import rs.ac.uns.ftn.backend.dto.response.OwnerEntityDTO;
import rs.ac.uns.ftn.backend.dto.response.UserDeleteDTO;
import rs.ac.uns.ftn.backend.model.*;
import rs.ac.uns.ftn.backend.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Service
@Transactional
public class AdminDeleteService {

    @Autowired
    private MyUserRepository mur;

    @Autowired
    private DeleteRequestRepository drr;


    @Autowired
    private CottageOwnerRepository cor;


    @Autowired
    private BoatOwnerRepository bor;


    @Autowired
    private InstructorRepository ir;

    @Async
    public CompletableFuture<Boolean> acceptDeleteUser(String username) {

        log.info("DELETE USER "+ username);

        MyUser mu = mur.findByUsername(username);

        mu.setEnabled(false);

        mur.save(mu);

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> declineDeleteUser(String username) {

        log.info("DECLINE USER "+ username);


        MyUser mu = mur.findByUsername(username);

        mu.setDeactivate(true);

        mur.save(mu);

        return CompletableFuture.completedFuture(true);

    }


    @Async
    public CompletableFuture<List<UserDeleteDTO>> findAllDeletedUsers() {

        log.info("GET ALL DELETED USERS");


        List<DeleteRequest> dr =  drr.findAll();
        List<UserDeleteDTO> l = dr.stream().map(
                                                  d-> new UserDeleteDTO(d.getMyUser().getId(),d.getMyUser().getEmail(),d.getMyUser().getUsername(),
                                                  d.getMyUser().getPassword(),
                                                  d.getMyUser().getFirstName(),d.getMyUser().getLastName(),d.getDescription())
                                                 ).collect(Collectors.toList());


        return CompletableFuture.completedFuture(l);

    }



    @Async
    public CompletableFuture<List<OwnerEntityDTO>> findAllCottageOwner() {

        log.info("GET ALL COTTAGE OWNER");


        List<CottageOwner> co =  cor.findAll();
        List<OwnerEntityDTO> l = co.stream().map(
                d-> new OwnerEntityDTO(
                        d.getId(),d.getName(),d.getEmail()
                )
        ).collect(Collectors.toList());


        return CompletableFuture.completedFuture(l);

    }

    @Async
    public CompletableFuture<Boolean> deleteCottageOwner(Long id) {

        log.info("DELETE COTTAGE OWNER "+id);

        Optional<CottageOwner> co = cor.findById(id);

        co.get().setDelete(true);

        cor.save(co.get());

        return CompletableFuture.completedFuture(true);

    }


    @Async
    public CompletableFuture<List<OwnerEntityDTO>> findAllBoatOwner() {

        log.info("GET ALL BOAT OWNER");


        List<BoatOwner> bo =  bor.findAll();
        List<OwnerEntityDTO> l = bo.stream().map(
                d-> new OwnerEntityDTO(
                        d.getId(),d.getName(),d.getEmail()
                )
        ).collect(Collectors.toList());


        return CompletableFuture.completedFuture(l);

    }

    @Async
    public CompletableFuture<Boolean> deleteBoatOwner(Long id) {

        log.info("DELETE BOAT OWNER "+id);

        Optional<BoatOwner> bo = bor.findById(id);

        bo.get().setDelete(true);

        bor.save(bo.get());

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> deleteInstructor(Long id) {

        log.info("DELETE INSTRUCTOR "+id);

        Optional<Instructor> i = ir.findById(id);

        i.get().setDelete(true);

        ir.save(i.get());

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<List<OwnerEntityDTO>> findAllInstructor() {

        log.info("GET ALL INSTRUCTOR");


        List<Instructor> i =  ir.findAll();
        List<OwnerEntityDTO> l = i.stream().map(
                d-> new OwnerEntityDTO(
                        d.getId(),d.getName(),d.getEmail()
                )
        ).collect(Collectors.toList());


        return CompletableFuture.completedFuture(l);

    }

}
