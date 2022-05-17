package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.*;
import rs.ac.uns.ftn.backend.dto.response.BoatReservationCalendarDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageReservationCalendarDTO;
import rs.ac.uns.ftn.backend.model.*;
import rs.ac.uns.ftn.backend.repository.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class BoatReservationService {
    private MyUserRepository mr;

    private BoatRepository br;

    private BoatReservationRepository brr;

    private BoatPricelistRepository bpr;


    public BoatReservationService(BoatRepository br, MyUserRepository mr,BoatReservationRepository brr,BoatPricelistRepository bpr){
        this.br = br;
        this.mr = mr;
        this.brr = brr;
        this.bpr=bpr;
    }

    @Async
    public CompletableFuture<BoatReservationCalendarDTO> getBoatReservations(Long id) {

        BoatReservationCalendarDTO brc = new BoatReservationCalendarDTO();

        Optional<Boat> bto = br.findById(id);

        Boat bt = bto.get();

//        System.out.println(ct);
//        List<MyUser> stringsList = new ArrayList<>(ct.getMyUsers());
//        System.out.println(stringsList.get(0).getUsername());

//        System.out.println("LISTA" + ct.getCottageResevations());

        brc.setBoatResevations(bt.getBoatResevations().stream().collect(Collectors.toList()));


        List<SimpleActionDTO> lista = bt.getBoatActions().stream().map(
                e-> new SimpleActionDTO(e.getStartAction(),e.getEndAction())
        ).collect(Collectors.toList());

//        crc.setCottageActions(lista);
        SimpleActionDTO temp= new SimpleActionDTO();
        Boolean cmp= true;
        for(SimpleActionDTO sac : lista){
            for(BoatResevation botres : bt.getBoatResevations()){


                temp = sac;
                if(sac.getStartAction().equals(botres.getReservationStart()) && sac.getEndAction().equals(botres.getReservationEnd())){
//                    crc.getCottageActionsReservated().add(sac);
                    cmp=false;
                    break;
                }
            }
            if(cmp){
                if(temp.getEndAction().isBefore(LocalDate.now())){
                    brc.getBoatActionsUnReservated().add(temp);
                }
                else {
                    brc.getBoatActionsReservated().add(temp);
                }
            }
            cmp= true;

        }

        return CompletableFuture.completedFuture(brc);
    }

    public BoatPricelist getPricelist(String desc) {

        BoatPricelist bpls = bpr.findByDescription(desc);

        return bpls;
    }


    @Async
    public CompletableFuture<Boolean> saveReservation(SaveBoatReservationDTO srdto) {

        log.info("SAVE RESERVATION WITH USERNAME: " + srdto.getMyUsername());


        BoatResevation botr = new BoatResevation();

        botr.setActive(true);

        botr.setReservationStart(srdto.getStart());

        botr.setReservationEnd(srdto.getEnd());

        botr.setPricelistItem(getPricelist(srdto.getDescription()));

        MyUser muo = mr.findByUsername(srdto.getMyUsername());

        muo.getBoatResevations().add(botr);

        Optional<Boat> bto = br.findById(srdto.getBoatId());

        Boat bt = bto.get();

        bt.getBoatResevations().add(botr);

        br.save(bt);

//        crr.save(cotr);

        mr.save(muo);

        return CompletableFuture.completedFuture(true);
    }

    @Async
    public CompletableFuture<Boolean> deleteReservation(DeleteBoatReservationDTO drdto) {

        log.info("DELETE RESERVATION WITH ID: " + drdto.getReservationId());


        MyUser muo = mr.findByUsername(drdto.getMyUsername());

        Optional<BoatResevation> bt2 = muo.getBoatResevations().stream().filter(btt -> btt.getId().equals(drdto.getReservationId())).findFirst();

        BoatResevation btt2 = bt2.get();

        Period period = Period.between(LocalDate.now(),btt2.getReservationStart());

        if(period.getDays()>3){
            muo.getBoatResevations().remove(btt2);
            btt2.setActive(false);
        }
        else {
            return CompletableFuture.completedFuture(false);
        }

        Optional<Boat> bto = br.findById(drdto.getBoateId());

        Boat bt = bto.get();

        bt.getBoatResevations().remove(btt2);

        return CompletableFuture.completedFuture(true);
    }
}
