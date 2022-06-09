package rs.ac.uns.ftn.backend.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.RegisterUserDTO;
import rs.ac.uns.ftn.backend.dto.request.ValidationAccountTokenDTO;
import rs.ac.uns.ftn.backend.dto.response.*;
import rs.ac.uns.ftn.backend.model.*;
import rs.ac.uns.ftn.backend.repository.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
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
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CottageRepository crr;

    @Autowired
    private BoatRepository brr;

    @Autowired
    private AdventureRepository arr;

    @Autowired
    private EmailSenderService service;

    @Autowired
    private RoleRepository rr;

    @Async
    public CompletableFuture<Boolean> registerUser(RegisterUserDTO rudto) {

        log.info("REGISTER USER WITH USERNAME: " + rudto.getGrad());

        MyUser mu = new MyUser();

        List<Role> list_role = rr.findByName("ROLE_USER");

        String randomRegisterToken =UUID.randomUUID().toString();

        mu.setAdresa(rudto.getAdresa());
        mu.setEmail(rudto.getEmail());
        mu.setPassword(passwordEncoder.encode(rudto.getPassword()));
        mu.setDrzava(rudto.getDrzava());
        mu.setLastName(rudto.getLastName());
        mu.setFirstName(rudto.getFirstName());
        mu.setUsername(rudto.getUsername());
        mu.setPhoneNumber(rudto.getPhoneNumber());
        mu.setGrad(rudto.getGrad());
        mu.setPoint(0l);
        mu.setEnabled(true);
        mu.setLastPasswordResetDate(Timestamp.valueOf("2017-10-01 21:58:58.508"));
        mu.setAdventureResevations(new HashSet<>());
        mu.setBoatResevations(new HashSet<>());
        mu.setCottageResevations(new HashSet<>());
        mu.setCottages(new HashSet<>());
        mu.setBoats(new HashSet<>());
        mu.setAdventures(new HashSet<>());
        mu.setRegisterToken(randomRegisterToken);
        mu.setActivate(false);


        mu.setRoles(list_role);

        mur.save(mu);

        //when user is saved send validation mail
        service.sendSimpleEmail("jeremy.cartwright96@ethereal.email",
                "USER VALIDATION TOKEN IS : " + randomRegisterToken,
                "Register user validation mail for user" + mu.getUsername()
        );

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<ValidationAccountTokenDTO> isActivate(String username) {

        log.info("VAT USER WITH USERNAME: " + username);

        MyUser mu = mur.findByUsername(username);

        ValidationAccountTokenDTO vat = new ValidationAccountTokenDTO();

        vat.setActivate(mu.getActivate());
        vat.setToken(mu.getRegisterToken());

        return CompletableFuture.completedFuture(vat);

    }

    @Async
    public CompletableFuture<Boolean> validateAcc(String username) {

        log.info("VALUDATE USER WITH USERNAME: " + username);

        MyUser mu = mur.findByUsername(username);

        mu.setActivate(true);

        mur.save(mu);

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<MyUserDTO> getOneMyUser(String username) {

        log.info("GET ONE USER WITH EMAIL: " + username);

        MyUser mu = mur.findByUsername(username);

        MyUserDTO mudto = new MyUserDTO(
                mu.getId(),mu.getEmail(),mu.getUsername(),mu.getPassword(),mu.getFirstName(),mu.getLastName(),mu.getRoles()
                ,mu.getPoint(),mu.getPhoneNumber(),mu.getAdresa(),mu.getGrad(),mu.getDrzava()
            );

        return CompletableFuture.completedFuture(mudto);

    }

    @Async
    public CompletableFuture<Boolean> editMyUser(MyUserDTO mud) {

        log.info("EDIT ONE USER WITH EMAIL: " + mud.getUsername());
//        System.out.println("USERNAME:" + mud.getUsername());
//        System.out.println("PASSWORD:" + mud.getPassword());
        MyUser mu = mur.findByUsername(mud.getUsername());

        mu.setFirstName(mud.getFirstName());
        mu.setLastName(mud.getLastName());
//        System.out.println("Passwor" + passwordEncoder.encode(mud.getPassword()));
        mu.setPassword(passwordEncoder.encode(mud.getPassword()));
        mu.setEmail(mud.getEmail());
        mu.setPoint(mud.getPoint());
        mu.setPhoneNumber(mud.getPhoneNumber());
        mu.setAdresa(mud.getAdresa());
        mu.setGrad(mud.getGrad());
        mu.setDrzava(mud.getDrzava());

        mur.save(mu);

        return CompletableFuture.completedFuture(true);

    }

    // COTTAGE
    @Async
    public CompletableFuture<List<CottageReservationHistoryDTO>> getAllHistoryReservationCottage(String username , Integer pageNum ) {

        MyUserService.log.info("GET ALL COTTAGES HISTORY RESERVATIONS BEST WAY "+ Thread.currentThread().getName());

        MyUser mu = mur.findByUsername(username);

        for(CottageResevation g : mu.getCottageResevations()){
            System.out.println("ID JE " + g.getId());
        }

        System.out.println("Broj rezervacija je "+mu.getCottageResevations().size()  );

        List<CottageReservationHistoryDTO> ctr = mu.getCottageResevations().stream().map(cr->

                        new CottageReservationHistoryDTO(cr.getId(),cr.getReservationStart(),
                        cr.getReservationEnd(),cr.getActive(),cr.getPricelistItem(),findCottageId(cr.getId())))

                .collect(Collectors.toList());

        System.out.println("KRAJJJJ "+mu.getCottageResevations().size());
        PagedListHolder page = new PagedListHolder(ctr);

        page.setPageSize(5); // number of items per page
        page.setPage(pageNum);      // set to first page

        return CompletableFuture.completedFuture(page.getPageList());

    }

    @Async
    public CompletableFuture<List<CottageReservationHistoryDTO>> getAllNotHeldReservationCottage(String username , Integer pageNum ) {

        MyUserService.log.info("GET ALL NOT HELD COTTAGES HISTORY RESERVATIONS BEST WAY "+ Thread.currentThread().getName());

        MyUser mu = mur.findByUsername(username);
        List<CottageReservationHistoryDTO> ctr = mu.getCottageResevations().stream().map(cr->

                        new CottageReservationHistoryDTO(cr.getId(),cr.getReservationStart(),
                                cr.getReservationEnd(),cr.getActive(),cr.getPricelistItem(),findCottageId(cr.getId())))

                .collect(Collectors.toList());
        //not held ctr
        List<CottageReservationHistoryDTO> nhctr = ctr.stream().filter(e->e.getReservationStart().isAfter(LocalDate.now())).collect(Collectors.toList());

        PagedListHolder page = new PagedListHolder(nhctr);

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

    @Async
    public CompletableFuture<List<BoatReservationHistoryDTO>> getAllNotHeldReservationBoat(String username , Integer pageNum ) {

        MyUserService.log.info("GET ALL BOATS HISTORY RESERVATIONS BEST WAY "+ Thread.currentThread().getName());


        MyUser mu = mur.findByUsername(username);

        List<BoatReservationHistoryDTO> btr = mu.getBoatResevations().stream().map(cr->

                        new BoatReservationHistoryDTO(cr.getId(),cr.getReservationStart(),
                                cr.getReservationEnd(),cr.getActive(),cr.getPricelistItem(),findBoatId(cr.getId())))

                .collect(Collectors.toList());

        //not held ctr
        List<BoatReservationHistoryDTO> nhbtr = btr.stream().filter(e->e.getReservationStart().isAfter(LocalDate.now())).collect(Collectors.toList());


        PagedListHolder page = new PagedListHolder(nhbtr);


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

    @Async
    public CompletableFuture<List<AdventureReservationHistoryDTO>> getAllNotHeldReservationAdventure(String username , Integer pageNum ) {

        MyUserService.log.info("GET ALL ADVENTURES HISTORY RESERVATIONS BEST WAY "+ Thread.currentThread().getName());



        MyUser mu = mur.findByUsername(username);
        List<AdventureReservationHistoryDTO> atr = mu.getAdventureResevations().stream().map(ar->

                        new AdventureReservationHistoryDTO(ar.getId(),ar.getReservationStart(),
                                ar.getReservationEnd(),ar.getActive(),ar.getPricelistItem(),findAdventureId(ar.getId())))

                .collect(Collectors.toList());


        //not held ctr
        List<AdventureReservationHistoryDTO> nhatr = atr.stream().filter(e->e.getReservationStart().isAfter(LocalDate.now())).collect(Collectors.toList());


        PagedListHolder page = new PagedListHolder(nhatr);

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
