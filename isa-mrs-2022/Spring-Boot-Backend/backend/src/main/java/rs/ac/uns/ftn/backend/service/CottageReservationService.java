package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.DeleteCottageReservationDTO;
import rs.ac.uns.ftn.backend.dto.request.SaveCottageReservationDTO;
import rs.ac.uns.ftn.backend.dto.request.SimpleActionDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageReservationCalendarDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageReservationDTO;
import rs.ac.uns.ftn.backend.model.*;
import rs.ac.uns.ftn.backend.repository.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Transactional
@Service
public class CottageReservationService {

    private MyUserRepository mr;

    private CottageRepository cr;

    private CottageReservationRepository crr;

    private CottagePricelistRepository cpr;


    public CottageReservationService(CottageRepository cr, MyUserRepository mr,CottageReservationRepository crr,CottagePricelistRepository cpr){
        this.cr = cr;
        this.mr = mr;
        this.crr = crr;
        this.cpr=cpr;
    }

    @Async
    public CompletableFuture<CottageReservationCalendarDTO> getCottageReservations(Long id) {

        CottageReservationCalendarDTO crc = new CottageReservationCalendarDTO();

        Optional<Cottage> cto = cr.findById(id);

        Cottage ct = cto.get();

//        System.out.println(ct);
//        List<MyUser> stringsList = new ArrayList<>(ct.getMyUsers());
//        System.out.println(stringsList.get(0).getUsername());

//        System.out.println("LISTA" + ct.getCottageResevations());

        crc.setCottageResevations(ct.getCottageResevations().stream().collect(Collectors.toList()));


        List<SimpleActionDTO> lista = ct.getCottageActions().stream().map(
                e-> new SimpleActionDTO(e.getStartAction(),e.getEndAction())
        ).collect(Collectors.toList());

//        crc.setCottageActions(lista);
        SimpleActionDTO temp= new SimpleActionDTO();
        Boolean cmp= true;
        for(SimpleActionDTO sac : lista){
            for(CottageResevation cotres : ct.getCottageResevations()){


                temp = sac;
                if(sac.getStartAction().equals(cotres.getReservationStart()) && sac.getEndAction().equals(cotres.getReservationEnd())){
//                    crc.getCottageActionsReservated().add(sac);
                    cmp=false;
                    break;
                }
            }
            if(cmp){
                if(temp.getEndAction().isBefore(LocalDate.now())){
                    crc.getCottageActionsUnReservated().add(temp);
                }
                else {
                    crc.getCottageActionsReservated().add(temp);
                }
            }
            cmp= true;

        }

        return CompletableFuture.completedFuture(crc);
    }

    public CottagePricelist getPricelist( String desc) {

        CottagePricelist cpls = cpr.findByDescription(desc);

        return cpls;
    }


    @Async
    public CompletableFuture<Boolean> saveReservation(SaveCottageReservationDTO srdto) {

        log.info("SAVE RESERVATION WITH USERNAME: " + srdto.getMyUsername());


        CottageResevation cotr = new CottageResevation();

        cotr.setActive(true);

        cotr.setReservationStart(srdto.getStart());

        cotr.setReservationEnd(srdto.getEnd());

        cotr.setPricelistItem(getPricelist(srdto.getDescription()));

        MyUser muo = mr.findByUsername(srdto.getMyUsername());

        muo.getCottageResevations().add(cotr);

        Optional<Cottage> cto = cr.findById(srdto.getCottageId());

        Cottage ct = cto.get();

        ct.getCottageResevations().add(cotr);

        cr.save(ct);

//        crr.save(cotr);

        mr.save(muo);

        return CompletableFuture.completedFuture(true);
    }

    @Async
    public CompletableFuture<Boolean> deleteReservation(DeleteCottageReservationDTO drdto) {

        log.info("DELETE RESERVATION WITH ID: " + drdto.getReservationId());


        MyUser muo = mr.findByUsername(drdto.getMyUsername());

        Optional<CottageResevation> ct2 = muo.getCottageResevations().stream().filter(ctt -> ctt.getId().equals(drdto.getReservationId())).findFirst();

        CottageResevation ctt2 = ct2.get();

//        Period period = Period.between(LocalDate.now(),ctt2.getReservationStart());

        long daysBetween = DAYS.between(LocalDate.now(), ctt2.getReservationStart());

        if(daysBetween>3){
            muo.getCottageResevations().remove(ctt2);
            ctt2.setActive(false);
        }
        else {
            return CompletableFuture.completedFuture(false);
        }

        Optional<Cottage> cto = cr.findById(drdto.getCottageId());

        Cottage ct = cto.get();

        ct.getCottageResevations().remove(ctt2);

        return CompletableFuture.completedFuture(true);
    }

}
