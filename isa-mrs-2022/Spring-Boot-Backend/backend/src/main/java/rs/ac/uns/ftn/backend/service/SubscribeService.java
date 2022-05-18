package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.SubscribeDTO;

import rs.ac.uns.ftn.backend.dto.response.AdventureDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.model.Adventure;
import rs.ac.uns.ftn.backend.model.Boat;
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.model.MyUser;
import rs.ac.uns.ftn.backend.repository.AdventureRepository;
import rs.ac.uns.ftn.backend.repository.BoatRepository;
import rs.ac.uns.ftn.backend.repository.CottageRepository;
import rs.ac.uns.ftn.backend.repository.MyUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Slf4j
@Transactional
@Service
public class SubscribeService {

    @Autowired
    private CottageRepository cr;

    @Autowired
    private BoatRepository br;

    @Autowired
    private AdventureRepository ar;

    @Autowired
    private MyUserRepository mr;

    @Async
    public CompletableFuture<List<CottageDTO>> getAllSubCottage(String username){

        log.info("GET ALL SUB COTTAGE "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(username);

        List<CottageDTO> cdto = mu.getCottages().stream().map(
                c->
                        new CottageDTO(c.getId(),c.getName(), c.getCottageImages().stream().findFirst().orElse(null), c.getAddress(), Math.toIntExact(c.getNumberOfBedPerRoom()),c.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(cdto);
    }

    @Async
    public CompletableFuture<List<BoatDTO>> getAllSubBoat(String username){

        log.info("GET ALL SUB BOAT "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(username);

        List<BoatDTO> bdto = mu.getBoats().stream().map(
                b->
                        new BoatDTO(b.getId(),b.getName(), b.getBoatImages().stream().findFirst().orElse(null), b.getAddress(), b.getType(),b.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(bdto);
    }


    @Async
    public CompletableFuture<List<AdventureDTO>> getAllSubAdventure(String username){

        log.info("GET ALL SUB ADVENTURE "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(username);

        List<AdventureDTO> adto = mu.getAdventures().stream().map(
                a->
                        new AdventureDTO(a.getId(),a.getName(), a.getAdventureImages().stream().findFirst().orElse(null), a.getAddress(), a.getMaxNumPerson(),a.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(adto);
    }


    @Async
    public CompletableFuture<Boolean> subCottage(SubscribeDTO sdto) {

        log.info("SUB COTTAGE "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(sdto.getUsername());

        Optional<Cottage> ct = cr.findById(sdto.getEntityId());

        Cottage c = ct.get();

        c.getMyUsers().add(mu);

        mu.getCottages().add(c);

        cr.save(c);

        mr.save(mu);

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> isSubCottage(SubscribeDTO sdto) {

        log.info("IS SUB COTTAGE "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(sdto.getUsername());

        Optional<Cottage> c = mu.getCottages().stream().filter(e->e.getId().equals(sdto.getEntityId())).findFirst();

        if(c.isEmpty()){
            return CompletableFuture.completedFuture(false);
        }

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> subBoat(SubscribeDTO sdto) {

        log.info("SUB BOAT "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(sdto.getUsername());

        Optional<Boat> bt = br.findById(sdto.getEntityId());

        Boat b = bt.get();

        b.getMyUsers().add(mu);

        mu.getBoats().add(b);

        br.save(b);

        mr.save(mu);

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> isSubBoat(SubscribeDTO sdto) {

        log.info("IS SUB BOAT "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(sdto.getUsername());

        Optional<Boat> c = mu.getBoats().stream().filter(e->e.getId().equals(sdto.getEntityId())).findFirst();

        if(c.isEmpty()){
            return CompletableFuture.completedFuture(false);
        }

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> subAdventure(SubscribeDTO sdto) {

        log.info("SUB ADVENTURE "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(sdto.getUsername());

        Optional<Adventure> at = ar.findById(sdto.getEntityId());

        Adventure a = at.get();

        a.getMyUsers().add(mu);

        mu.getAdventures().add(a);

        ar.save(a);

        mr.save(mu);

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> isSubAdventure(SubscribeDTO sdto) {

        log.info("IS SUB ADVENTURE "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(sdto.getUsername());

        Optional<Adventure> c = mu.getAdventures().stream().filter(e->e.getId().equals(sdto.getEntityId())).findFirst();

        if(c.isEmpty()){
            return CompletableFuture.completedFuture(false);
        }

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> unsubCottage(SubscribeDTO sdto) {

        log.info("UNSUB COTTAGES "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(sdto.getUsername());

        Optional<Cottage> ct = cr.findById(sdto.getEntityId());

        Cottage c = ct.get();

        c.getMyUsers().remove(mu);

        mu.getCottages().remove(c);

        cr.save(c);

        mr.save(mu);

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> unsubBoat(SubscribeDTO sdto) {

        log.info("UNSUB BOAT "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(sdto.getUsername());

        Optional<Boat> bt = br.findById(sdto.getEntityId());

        Boat b = bt.get();

        b.getMyUsers().remove(mu);

        mu.getBoats().remove(b);

        br.save(b);

        mr.save(mu);

        return CompletableFuture.completedFuture(true);


    }

    @Async
    public CompletableFuture<Boolean> unsubAdventure(SubscribeDTO sdto) {

        log.info("UNSUB COTTAGES "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(sdto.getUsername());

        Optional<Adventure> at = ar.findById(sdto.getEntityId());

        Adventure a = at.get();

        a.getMyUsers().remove(mu);

        mu.getAdventures().remove(a);

        ar.save(a);

        mr.save(mu);

        return CompletableFuture.completedFuture(true);

    }

}
