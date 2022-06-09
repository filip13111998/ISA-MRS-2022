package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.*;
import rs.ac.uns.ftn.backend.dto.response.AdventureReservationCalendarDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageReservationCalendarDTO;
import rs.ac.uns.ftn.backend.model.*;
import rs.ac.uns.ftn.backend.repository.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Transactional
@Service
public class AdventureReservationService {

    private MyUserRepository mr;

    private AdventureRepository ar;

    private AdventureReservationRepository arr;

    private AdventurePricelistRepository apr;

    private AdventureActionRepository aar;

    public AdventureReservationService(AdventureActionRepository aar,AdventureRepository ar, MyUserRepository mr,AdventureReservationRepository arr,AdventurePricelistRepository apr){
        this.ar = ar;
        this.mr = mr;
        this.arr = arr;
        this.apr=apr;
        this.aar = aar;
    }

    @Async
    public CompletableFuture<AdventureReservationCalendarDTO> getAdventureReservations(Long id) {

        AdventureReservationCalendarDTO arc = new AdventureReservationCalendarDTO();

        Optional<Adventure> ato = ar.findById(id);

        Adventure at = ato.get();

//        System.out.println(at.getAdventureReservations().stream().collect(Collectors.toList()));
//        List<MyUser> stringsList = new ArrayList<>(ct.getMyUsers());
//        System.out.println(stringsList.get(0).getUsername());

//        System.out.println("LISTA" + ct.getCottageResevations());

        arc.setAdventureResevations(at.getAdventureReservations().stream().collect(Collectors.toList()));


        List<SimpleActionDTO> lista = at.getAdventureActions().stream().map(
                e-> new SimpleActionDTO(e.getStartAction(),e.getEndAction())
        ).collect(Collectors.toList());
//        System.out.println(lista);
//        crc.setCottageActions(lista);
        SimpleActionDTO temp= new SimpleActionDTO();
        Boolean cmp= true;



        for(SimpleActionDTO sac : lista){
            for(AdventureReservation aotres : at.getAdventureReservations()){
                temp = sac;
                if(sac.getStartAction().equals(aotres.getReservationStart()) && sac.getEndAction().equals(sac.getEndAction())){
//                    crc.getAdventureActionsReservated().add(sac);
//                    System.out.println(sac.getStartAction());
//                    System.out.println(aotres.getReservationStart());
//                    System.out.println(sac.getEndAction());
//                    System.out.println(sac.getEndAction());
                    cmp=false;
                    break;
                }
            }

            if(cmp){

//                System.out.println("USO" + temp);
                if(temp.getEndAction().isBefore(LocalDate.now())){
                    arc.getAdventureActionsUnReservated().add(temp);
                }
                else {
                    arc.getAdventureActionsReservated().add(temp);
                }
            }
            cmp=true;

        }

        return CompletableFuture.completedFuture(arc);
    }

    public AdventurePricelist getPricelist(String desc) {

        AdventurePricelist apls = apr.findByDescription(desc);

        return apls;
    }


    @Async
    public CompletableFuture<Boolean> saveReservation(SaveAdventureReservationDTO srdto) {

        log.info("SAVE RESERVATION WITH USERNAME: " + srdto.getMyUsername());

        List<AdventureAction> aal = aar.findAll();

        Optional<AdventureAction> aa = aal.stream().filter(e->e.getStartAction().equals(srdto.getStart())).findFirst();

        Optional<Adventure> aot = ar.findById(srdto.getAdventureId());

//        System.out.println("USO");
//        System.out.println(srdto.getStart());

        if (!aa.isEmpty()){
//            System.out.println("USO");
            aot.get().getAdventureActions().remove(aa.get());
            aar.delete(aa.get());
        }


        AdventureReservation aotr = new AdventureReservation();

        aotr.setActive(true);

        aotr.setReservationStart(srdto.getStart());

        aotr.setReservationEnd(srdto.getEnd());

        aotr.setPricelistItem(getPricelist(srdto.getDescription()));

        MyUser muo = mr.findByUsername(srdto.getMyUsername());

        muo.getAdventureResevations().add(aotr);

        Optional<Adventure> ato = ar.findById(srdto.getAdventureId());

        Adventure at = ato.get();

        at.getAdventureReservations().add(aotr);

        arr.save(aotr);

        ar.save(at);

//        crr.save(cotr);

        mr.save(muo);

        return CompletableFuture.completedFuture(true);
    }

    @Async
    public CompletableFuture<Boolean> deleteReservation(DeleteAdventureReservationDTO drdto) {

        log.info("DELETE RESERVATION WITH ID: " + drdto.getReservationId());


        MyUser muo = mr.findByUsername(drdto.getMyUsername());

        Optional<AdventureReservation> at2 = muo.getAdventureResevations().stream().filter(att -> att.getId().equals(drdto.getReservationId())).findFirst();

        AdventureReservation att2 = at2.get();

//        Period period = Period.between(LocalDate.now(),att2.getReservationStart());
        long daysBetween = DAYS.between(LocalDate.now(), att2.getReservationStart());

        if(daysBetween>3){
            muo.getAdventureResevations().remove(att2);
            att2.setActive(false);
        }
        else {
            return CompletableFuture.completedFuture(false);
        }

        Optional<Adventure> ato = ar.findById(drdto.getAdventureId());

        Adventure at = ato.get();

        at.getAdventureReservations().remove(att2);

        return CompletableFuture.completedFuture(true);
    }

}
