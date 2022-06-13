package rs.ac.uns.ftn.backend.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.BoatSearchSortDTO;
import rs.ac.uns.ftn.backend.dto.request.ReportSearchDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatDTO;
import rs.ac.uns.ftn.backend.dto.response.ReportDTO;
import rs.ac.uns.ftn.backend.model.*;
import rs.ac.uns.ftn.backend.repository.AdventureReservationRepository;
import rs.ac.uns.ftn.backend.repository.BoatReservationRepository;
import rs.ac.uns.ftn.backend.repository.CottageReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ReportService {

    @Autowired
    private CottageReservationRepository crr;

    @Autowired
    private BoatReservationRepository brr;

    @Autowired
    private AdventureReservationRepository arr;


    @Async
    public CompletableFuture<ReportDTO> report(ReportSearchDTO rsd) {

        log.info("REPORT START IS: " + rsd.getStart());

        log.info("REPORT END IS: " + rsd.getEnd());

        ReportDTO r = new ReportDTO();

        List<CottageResevation> cr = crr.findAll();

        List<BoatResevation> br = brr.findAll();

        List<AdventureReservation> ar = arr.findAll();


        List<CottageResevation> cr_new = cr.stream().filter(e -> this.checkCottage(e,rsd) ).collect(Collectors.toList());

        List<BoatResevation> br_new = br.stream().filter(e -> this.checkBoat(e,rsd) ).collect(Collectors.toList());

        List<AdventureReservation> ar_new = ar.stream().filter(e -> this.checkAdventure(e,rsd) ).collect(Collectors.toList());

        r.setNumberCottageReservation(cr_new.stream().count());

        r.setNumberBoatReservation(br_new.stream().count());

        r.setNumberAdventureReservation(ar_new.stream().count());

        List<Long> ctg_sum = cr_new.stream().map(e-> e.getPrice()).collect(Collectors.toList());

        r.setPriceCottageReservation(ctg_sum.stream().mapToLong(Long::longValue).sum());


        List<Long> bt_sum = br_new.stream().map(e-> e.getPrice()).collect(Collectors.toList());

        r.setPriceBoatReservation(bt_sum.stream().mapToLong(Long::longValue).sum());

        List<Long> at_sum = ar_new.stream().map(e-> e.getPrice()).collect(Collectors.toList());

        r.setPriceAdventureReservation(at_sum.stream().mapToLong(Long::longValue).sum());

        r.setSum(r.getPriceCottageReservation() + r.getPriceBoatReservation() + r.getPriceAdventureReservation());

        r.setAppProfit((r.getSum()/100)*LoyalityProgram.appProfit );

        r.setFinishSum(r.getSum()-r.getAppProfit());

        System.out.println(r.getNumberAdventureReservation());
        System.out.println(r.getNumberBoatReservation());
        System.out.println(r.getNumberCottageReservation());

        return CompletableFuture.completedFuture(r);

    }

    public Boolean checkCottage(CottageResevation cr, ReportSearchDTO rs){

        if(rs.getStart() != null && rs.getEnd()!= null){
            System.out.println("USO CTG");
            if(rs.getStart().isBefore(cr.getReservationStart()) && rs.getEnd().isAfter(cr.getReservationStart())){
                return true;
            }else{
                return false;
            }
        }
        else if(rs.getStart() != null){
            if(rs.getStart().isBefore(cr.getReservationStart())){
                return true;
            }else{
                return false;
            }
        }
        else if(rs.getEnd()!= null){
            if(rs.getEnd().isAfter(cr.getReservationStart())){
                return true;
            }else{
                return false;
            }
        }

        return true;
    }

    public Boolean checkBoat(BoatResevation br, ReportSearchDTO rs){

        if(rs.getStart() != null && rs.getEnd()!= null){
            if(rs.getStart().isBefore(br.getReservationStart()) && rs.getEnd().isAfter(br.getReservationStart())){
                return true;
            }else{
                return false;
            }
        }
        else if(rs.getStart() != null){
            if(rs.getStart().isBefore(br.getReservationStart())){
                return true;
            }else{
                return false;
            }
        }
        else if(rs.getEnd()!= null){
            if(rs.getEnd().isAfter(br.getReservationStart())){
                return true;
            }else{
                return false;
            }
        }

        return true;
    }

    public Boolean checkAdventure(AdventureReservation ar, ReportSearchDTO rs){

        if(rs.getStart() != null && rs.getEnd()!= null){
            if(rs.getStart().isBefore(ar.getReservationStart()) && rs.getEnd().isAfter(ar.getReservationStart())){
                return true;
            }else{
                return false;
            }
        }
        else if(rs.getStart() != null){
            if(rs.getStart().isBefore(ar.getReservationStart())){
                return true;
            }else{
                return false;
            }
        }
        else if(rs.getEnd()!= null){
            if(rs.getEnd().isAfter(ar.getReservationStart())){
                return true;
            }else{
                return false;
            }
        }

        return true;
    }


}
