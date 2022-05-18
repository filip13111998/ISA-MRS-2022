package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.SubscribeDTO;
import rs.ac.uns.ftn.backend.model.*;
import rs.ac.uns.ftn.backend.repository.AdventureRepository;
import rs.ac.uns.ftn.backend.repository.BoatRepository;
import rs.ac.uns.ftn.backend.repository.CottageRepository;
import rs.ac.uns.ftn.backend.repository.MyUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@Service
public class MyUserActionService {

    @Autowired
    private CottageRepository cr;

    @Autowired
    private BoatRepository br;

    @Autowired
    private AdventureRepository ar;

    @Autowired
    private MyUserRepository mr;

    @Async
    public CompletableFuture<List<CottageAction>> getAllCottageAction(String username) {

        log.info("MY COTTAGE ACTIONS"+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(username);

        List<CottageAction> cta = new ArrayList<CottageAction>();

        mu.getCottages().stream().forEach(e->  cta.addAll(e.getCottageActions()));

//        List<CottageAction> cta = new ArrayList<CottageAction>();
//
//        for(Cottage c : mu.getCottages()){
//            cta.addAll(c.getCottageActions());
//        }

        return CompletableFuture.completedFuture(cta);

    }

    @Async
    public CompletableFuture<List<BoatAction>> getAllBoatAction(String username) {

        log.info("MY BOAT ACTIONS "+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(username);

        List<BoatAction> bta = new ArrayList<BoatAction>();

        mu.getBoats().stream().forEach(e->  bta.addAll(e.getBoatActions()));

//        List<CottageAction> cta = new ArrayList<CottageAction>();
//
//        for(Cottage c : mu.getCottages()){
//            cta.addAll(c.getCottageActions());
//        }

        return CompletableFuture.completedFuture(bta);

    }


    @Async
    public CompletableFuture<List<AdventureAction>> getAllAdventureAction(String username) {

        log.info("MY ADVENTURE ACTIONS"+ Thread.currentThread().getName());

        MyUser mu = mr.findByUsername(username);

        List<AdventureAction> ata = new ArrayList<AdventureAction>();

        mu.getAdventures().stream().forEach(e->  ata.addAll(e.getAdventureActions()));

//        List<CottageAction> cta = new ArrayList<CottageAction>();
//
//        for(Cottage c : mu.getCottages()){
//            cta.addAll(c.getCottageActions());
//        }

        return CompletableFuture.completedFuture(ata);

    }
}
