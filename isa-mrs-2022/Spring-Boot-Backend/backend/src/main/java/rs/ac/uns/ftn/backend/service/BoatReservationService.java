package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Transactional
@Service
public class BoatReservationService {
    private MyUserRepository mr;

    private BoatRepository br;

    private BoatReservationRepository brr;

    private BoatPricelistRepository bpr;

    private BoatActionRepository bar;

    @Autowired
    private EmailSenderService service;

    public BoatReservationService(BoatActionRepository bar,BoatRepository br, MyUserRepository mr,BoatReservationRepository brr,BoatPricelistRepository bpr){
        this.br = br;
        this.mr = mr;
        this.brr = brr;
        this.bpr=bpr;
        this.bar=bar;
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

        log.info("SAVE BOAT RESERVATION WITH USERNAME: " + srdto.getMyUsername());

        List<BoatAction> bal = bar.findAll();

        Optional<BoatAction> ba = bal.stream().filter(e->e.getStartAction().equals(srdto.getStart())).findFirst();

        Optional<Boat> bot = br.findById(srdto.getBoatId());



        List<BoatResevation> b2 =  bot.get().getBoatResevations().stream().filter(c->
                (
                        c.getReservationStart().equals(srdto.getStart())  || c.getReservationEnd().equals(srdto.getStart()) ||
                                c.getReservationStart().equals(srdto.getEnd())  || c.getReservationEnd().equals(srdto.getEnd())

                )
        ).collect(Collectors.toList());
        if(b2.size() >0){
            log.info("WRONG" );
            return CompletableFuture.completedFuture(false);
        }

        List<BoatResevation> b = bot.get().getBoatResevations().stream().filter(
                        c-> (  c.getReservationStart().isBefore(srdto.getStart())  &&
                                c.getReservationEnd().isAfter(srdto.getStart()))
                                ||  c.getReservationStart().isAfter(srdto.getEnd())   && c.getReservationEnd().isBefore(srdto.getEnd())
                                || (c.getReservationStart().isAfter(srdto.getStart()) && c.getReservationEnd().isAfter(srdto.getStart())
                                && c.getReservationStart().isBefore(srdto.getEnd()) && c.getReservationEnd().isBefore(srdto.getEnd())    )
                )
                .collect(Collectors.toList());

        if(b.size() >0){
            log.info("WRONG" );
            return CompletableFuture.completedFuture(false);
        }
//        System.out.println("USO");
//        System.out.println(srdto.getStart());

        if (!ba.isEmpty()){
//            System.out.println("USO");
            bot.get().getBoatActions().remove(ba.get());
            bar.delete(ba.get());
        }

        MyUser muo = mr.findByUsername(srdto.getMyUsername());

        BoatResevation botr = new BoatResevation();

        botr.setActive(true);

        botr.setReservationStart(srdto.getStart());

        botr.setReservationEnd(srdto.getEnd());

        botr.setPricelistItem(getPricelist(srdto.getDescription()));

        muo.setPoint(muo.getPoint()+5);


        Double procenat =  (double)(100- LoyalityProgram.getPercent(muo.getPoint()))/100;

        Long myPrice = Math.round(( Math.round(srdto.getPrice()))*(srdto.getStart().until(srdto.getEnd(), ChronoUnit.DAYS)+1)*procenat);

        botr.setPrice(myPrice);

        muo.getBoatResevations().add(botr);

        Optional<Boat> bto = br.findById(srdto.getBoatId());

        Boat bt = bto.get();

        bt.getBoatResevations().add(botr);

        brr.save(botr);

//        br.save(bt);

//        crr.save(cotr);

        mr.save(muo);

        service.sendSimpleEmail("jeremy.cartwright96@ethereal.email",
                "You are make succesfull boat reservation" ,
                "Boat reservation for user: " + muo.getUsername()
        );

        return CompletableFuture.completedFuture(true);
    }

    @Async
    public CompletableFuture<Boolean> deleteReservation(DeleteBoatReservationDTO drdto) {

        log.info("DELETE RESERVATION WITH ID: " + drdto.getReservationId());


        MyUser muo = mr.findByUsername(drdto.getMyUsername());
        System.out.println(muo);
        Optional<BoatResevation> bt2 = muo.getBoatResevations().stream().filter(btt -> btt.getId().equals(drdto.getReservationId())).findFirst();

        BoatResevation btt2 = bt2.get();
//        System.out.println(btt2);
//        Period period = Period.between(LocalDate.now(),btt2.getReservationStart());
//        System.out.println(period.getDays()+"DANI");
//        System.out.println(LocalDate.now()+"NOW");
//        System.out.println(btt2.getReservationStart()+"RES");

//        System.out.println(daysBetween);

        long daysBetween = DAYS.between(LocalDate.now(), btt2.getReservationStart());
        if(daysBetween>3){
            muo.getBoatResevations().remove(btt2);
            btt2.setActive(false);
        }
        else {
            return CompletableFuture.completedFuture(false);
        }

        Optional<Boat> bto = br.findById(drdto.getBoatId());

        Boat bt = bto.get();

        bt.getBoatResevations().remove(btt2);

        return CompletableFuture.completedFuture(true);
    }
}
