package rs.ac.uns.ftn.backend.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.CottageSearchSortDTO;
import rs.ac.uns.ftn.backend.dto.response.*;
import rs.ac.uns.ftn.backend.model.*;
import rs.ac.uns.ftn.backend.repository.AdventureRepository;
import rs.ac.uns.ftn.backend.repository.BoatRepository;
import rs.ac.uns.ftn.backend.repository.CottageRepository;
import rs.ac.uns.ftn.backend.repository.MyUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private CottageRepository crr;

    @Autowired
    private BoatRepository brr;

    @Autowired
    private AdventureRepository arr;

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

    // COTTAGE
    @Async
    public CompletableFuture<List<CottageReservationHistoryDTO>> getAllHistoryReservationCottage(String username , Integer pageNum ) {

        MyUserService.log.info("GET ALL COTTAGES HISTORY RESERVATIONS BEST WAY "+ Thread.currentThread().getName());

        MyUser mu = mur.findByUsername(username);
        List<CottageReservationHistoryDTO> ctr = mu.getCottageResevations().stream().map(cr->

                        new CottageReservationHistoryDTO(cr.getId(),cr.getReservationStart(),
                        cr.getReservationEnd(),cr.getActive(),cr.getPricelistItem(),findCottageId(cr.getId())))

                .collect(Collectors.toList());


        PagedListHolder page = new PagedListHolder(ctr);

        page.setPageSize(5); // number of items per page
        page.setPage(pageNum);      // set to first page

        return CompletableFuture.completedFuture(page.getPageList());

    }

    public Long findCottageId(Long cri){
        Optional<Cottage> id = crr.findAll().stream().filter(e-> findCottageId(e,cri) ).findFirst();

        return id.get().getId();
    }

    public Boolean findCottageId(Cottage cotres,Long ids){
        Optional<CottageResevation> id = cotres.getCottageResevations().stream().filter(e-> e.getId().equals(ids) ).findFirst();

        return !id.isEmpty();
    }

    // Boat

    @Async
    public CompletableFuture<List<BoatReservationHistoryDTO>> getAllHistoryReservationBoat(String username , Integer pageNum ) {

        MyUserService.log.info("GET ALL BOATS HISTORY RESERVATIONS BEST WAY "+ Thread.currentThread().getName());


        MyUser mu = mur.findByUsername(username);

        List<BoatReservationHistoryDTO> btr = mu.getBoatResevations().stream().map(cr->

                        new BoatReservationHistoryDTO(cr.getId(),cr.getReservationStart(),
                                cr.getReservationEnd(),cr.getActive(),cr.getPricelistItem(),findBoatId(cr.getId())))

                .collect(Collectors.toList());


        PagedListHolder page = new PagedListHolder(btr);

        page.setPageSize(5); // number of items per page
        page.setPage(pageNum);      // set to first page

        return CompletableFuture.completedFuture(page.getPageList());

    }

    public Long findBoatId(Long cri){
        Optional<Boat> id = brr.findAll().stream().filter(e-> findBoatId(e,cri) ).findFirst();

        return id.get().getId();
    }

    public Boolean findBoatId(Boat botres,Long ids){
        Optional<BoatResevation> id = botres.getBoatResevations().stream().filter(e-> e.getId().equals(ids) ).findFirst();

        return !id.isEmpty();
    }


    // ADVENTURE

    @Async
    public CompletableFuture<List<AdventureReservationHistoryDTO>> getAllHistoryReservationAdventure(String username , Integer pageNum ) {

        MyUserService.log.info("GET ALL ADVENTURES HISTORY RESERVATIONS BEST WAY "+ Thread.currentThread().getName());



        MyUser mu = mur.findByUsername(username);
        List<AdventureReservationHistoryDTO> atr = mu.getAdventureResevations().stream().map(ar->

                        new AdventureReservationHistoryDTO(ar.getId(),ar.getReservationStart(),
                                ar.getReservationEnd(),ar.getActive(),ar.getPricelistItem(),findAdventureId(ar.getId())))

                .collect(Collectors.toList());


        PagedListHolder page = new PagedListHolder(atr);

        page.setPageSize(5); // number of items per page
        page.setPage(pageNum);      // set to first page

        return CompletableFuture.completedFuture(page.getPageList());

    }

    public Long findAdventureId(Long ari){
        Optional<Adventure> id = arr.findAll().stream().filter(e-> findAdventureId(e,ari) ).findFirst();

        return id.get().getId();
    }

    public Boolean findAdventureId(Adventure advres, Long ids){
        Optional<AdventureReservation> id = advres.getAdventureReservations().stream().filter(e-> e.getId().equals(ids) ).findFirst();

        return !id.isEmpty();
    }


}
