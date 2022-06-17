package rs.ac.uns.ftn.backend.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.response.ComplaintAdminDTO;
import rs.ac.uns.ftn.backend.dto.response.ExtendComplaintDTO;

import rs.ac.uns.ftn.backend.dto.response.ExtendMarkAdminDTO;
import rs.ac.uns.ftn.backend.dto.response.MarkAdminDTO;
import rs.ac.uns.ftn.backend.model.*;
import rs.ac.uns.ftn.backend.repository.*;
import rs.ac.uns.ftn.backend.service.EmailSenderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Service
@Transactional
public class AdminComplaintMarkService {

    @Autowired
    private AdminRepository ad;


    @Autowired
    private CottageRepository cr;

    @Autowired
    private BoatRepository br;

    @Autowired
    private AdventureRepository ar;


    @Autowired
    private CottageMarkRepository cmr;

    @Autowired
    private BoatMarkRepository bmr;
    @Autowired
    private AdventureMarkRepository amr;
    @Autowired
    private CottageComplaintRepository ccr;
    @Autowired
    private BoatComplaintRepository bcr;
    @Autowired
    private AdventureComplaintRepository acr;


    @Autowired
    private EmailSenderService service;

    @Async
    public CompletableFuture<Boolean> acceptCottageMark(MarkAdminDTO dto) {

        log.info("ADD MARK COTTAGE WITH ID: " + dto.getId());

        Optional<CottageMark> cm = cmr.findById(dto.getId());

        Optional<Cottage> c = cr.findById(dto.getEntityID());

        cm.get().setEnable(true);

        cmr.save(cm.get());

        c.get().getMarks().add(cm.get());

        service.sendSimpleEmail("jeremy.cartwright96@ethereal.email",
                "New mark for adventure is: " + cm.get().getMark() ,
                "COTTAGE MARK ACCEPT response from admin"
        );

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> declineCottageMark(MarkAdminDTO dto) {

        log.info("DECLINE MARK COTTAGE WITH ID: " + dto.getId());

        Optional<CottageMark> cm = cmr.findById(dto.getId());

        cm.get().setEnable(true);

        cmr.save(cm.get());

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<List<ExtendMarkAdminDTO>> getAllCottageMark() {

        log.info("GET ALL COTTAGE MARKS");

        List<CottageMark> l =  cmr.findAll();;


        List<CottageMark> l3 = l.stream().filter(e->e.getEnable()==false).collect(Collectors.toList());

        List<ExtendMarkAdminDTO> l4 = l3.stream().map(e-> new ExtendMarkAdminDTO(e.getId(),e.getEntity(),cr.findById(e.getEntity()).get().getName(),e.getDate(),e.getMark())).collect(Collectors.toList());


        return CompletableFuture.completedFuture(l4);

    }

    @Async
    public CompletableFuture<Boolean> accepBoatMark(MarkAdminDTO dto) {

        log.info("ADD MARK BOAT WITH ID: " + dto.getId());

        Optional<BoatMark> bm = bmr.findById(dto.getId());

        Optional<Boat> b = br.findById(dto.getEntityID());

        bm.get().setEnable(true);

        bmr.save(bm.get());

        b.get().getMarks().add(bm.get());

        service.sendSimpleEmail("jeremy.cartwright96@ethereal.email",
                "New mark for adventure is: " + bm.get().getMark() ,
                "BOAT MARK ACCEPT response from admin"
        );

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> declineBoatMark(MarkAdminDTO dto) {

        log.info("DECLINE BOAT COTTAGE WITH ID: " + dto.getId());

        Optional<BoatMark> bm = bmr.findById(dto.getId());

        bm.get().setEnable(true);

        bmr.save(bm.get());

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<List<ExtendMarkAdminDTO>> getAllBoatMark() {

        log.info("GET ALL BOAT MARKS");

//        List<Set<BoatMark>> l = br.findAll().stream().map(e->e.getMarks()).collect(Collectors.toList());

        List<BoatMark> l = bmr.findAll();

        List<BoatMark> l2 = l.stream().filter(e->e.getEnable()==false).collect(Collectors.toList());

        List<ExtendMarkAdminDTO> l3 = l2.stream().map(e-> new ExtendMarkAdminDTO(e.getId(),e.getEntity(),br.findById(e.getEntity()).get().getName(),e.getDate(),e.getMark())).collect(Collectors.toList());


        return CompletableFuture.completedFuture(l3);

    }


    @Async
    public CompletableFuture<Boolean> acceptAdventureMark(MarkAdminDTO dto) {

        log.info("ADD MARK ADVENTURE WITH ID: " + dto.getId());

        Optional<AdventureMark> am = amr.findById(dto.getId());

        Optional<Adventure> a = ar.findById(dto.getEntityID());

        am.get().setEnable(true);

        amr.save(am.get());

        a.get().getMarks().add(am.get());

        service.sendSimpleEmail("jeremy.cartwright96@ethereal.email",
                "New mark for adventure is: " + am.get().getMark() ,
                "ADVENTURE MARK ACCEPT response from admin"
        );

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<Boolean> declineAdventureMark(MarkAdminDTO dto) {

        log.info("DECLINE MARK ADVENTURE WITH ID: " + dto.getId());

        Optional<AdventureMark> am = amr.findById(dto.getId());

        am.get().setEnable(true);

        amr.save(am.get());

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<List<ExtendMarkAdminDTO>> getAllAdventureMark() {

        log.info("GET ALL ADVENTURE MARKS");

        List<AdventureMark> l2 =  amr.findAll();

        List<AdventureMark> l3 = l2.stream().filter(e->e.getEnable()==false).collect(Collectors.toList());

        List<ExtendMarkAdminDTO> l4 = l3.stream().map(e-> new ExtendMarkAdminDTO(e.getId(),e.getEntity(),ar.findById(e.getEntity()).get().getName(),e.getDate(),e.getMark())).collect(Collectors.toList());


        return CompletableFuture.completedFuture(l4);

    }

    /*COMPLAINT PART*/
    @Async
    public synchronized CompletableFuture<Boolean> answerCottageComplaint(ComplaintAdminDTO cad) {

        log.info("ANSWER Complaint with id: " + cad.getId());

        Optional<CottageComplaint> cm = ccr.findById(cad.getId());

        try {

            if(cm.get().getEnable() == true){
                return CompletableFuture.completedFuture(false);
            }

            cm.get().setEnable(true);


        }
        catch (Exception e){
            return CompletableFuture.completedFuture(false);
        }

        ccr.save(cm.get());

        service.sendSimpleEmail("jeremy.cartwright96@ethereal.email",
                cad.getDescription() ,
                "Cottage Complaint response from admin"
        );

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<List<ExtendComplaintDTO>> getAllCottageComplaint() {

        log.info("GET ALL COMPLAINTS");

        List<CottageComplaint> com = ccr.findAll();

        List<CottageComplaint> l1 = com.stream().filter(e->!e.getEnable()).collect(Collectors.toList());

        List<ExtendComplaintDTO> l2 = l1.stream().map(
                e-> new ExtendComplaintDTO(e.getId(),e.getEntity() , cr.findById(e.getEntity()).get().getName(),e.getDate(),e.getDescription())


        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(l2);

    }


    @Async
    public synchronized CompletableFuture<Boolean> answerBoatComplaint(ComplaintAdminDTO cad) {

        log.info("ANSWER Complaint with id: " + cad.getId());

        Optional<BoatComplaint> cm = bcr.findById(cad.getId());

        try {
            if(cm.get().getEnable() == true){
                return CompletableFuture.completedFuture(false);
            }

            cm.get().setEnable(true);

            bcr.save(cm.get());
        }
        catch (Exception e){
            return CompletableFuture.completedFuture(false);
        }



        service.sendSimpleEmail("jeremy.cartwright96@ethereal.email",
                cad.getDescription() ,
                "Boat Complaint response from admin"
        );

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<List<ExtendComplaintDTO>> getAllBoatComplaint() {

        log.info("GET ALL COMPLAINTS");

        List<BoatComplaint> com = bcr.findAll();

        List<BoatComplaint> l1 = com.stream().filter(e->!e.getEnable()).collect(Collectors.toList());

        List<ExtendComplaintDTO> l2 = l1.stream().map(
                e-> new ExtendComplaintDTO(e.getId(),e.getEntity() , cr.findById(e.getEntity()).get().getName(),e.getDate(),e.getDescription())


        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(l2);

    }

    @Async
     public synchronized CompletableFuture<Boolean> answerAdventureComplaint(ComplaintAdminDTO cad) {

        log.info("ANSWER Complaint with id: " + cad.getId());

        Optional<AdventureComplaint> cm = acr.findById(cad.getId());

        try {
            if(cm.get().getEnable() == true){
                return CompletableFuture.completedFuture(false);
            }

            cm.get().setEnable(true);

            acr.save(cm.get());
        }
        catch (Exception e){
            return CompletableFuture.completedFuture(false);
        }


        service.sendSimpleEmail("jeremy.cartwright96@ethereal.email",
                cad.getDescription() ,
                "Adventure Complaint response from admin"
        );

        return CompletableFuture.completedFuture(true);

    }

    @Async
    public CompletableFuture<List<ExtendComplaintDTO>> getAllCAdventureComplaint() {

        log.info("GET ALL COMPLAINTS");

        List<AdventureComplaint> com = acr.findAll();

        List<AdventureComplaint> l1 = com.stream().filter(e->!e.getEnable()).collect(Collectors.toList());

        List<ExtendComplaintDTO> l2 = l1.stream().map(
                e-> new ExtendComplaintDTO(e.getId(),e.getEntity() , cr.findById(e.getEntity()).get().getName(),e.getDate(),e.getDescription())


        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(l2);

    }
}
