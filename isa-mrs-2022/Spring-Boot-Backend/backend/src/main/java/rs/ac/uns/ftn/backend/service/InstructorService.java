package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.InstructorDTO;
import rs.ac.uns.ftn.backend.dto.response.InstructorProfileDTO;
import rs.ac.uns.ftn.backend.model.Adventure;
import rs.ac.uns.ftn.backend.model.AdventureReservation;
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.model.Instructor;
import rs.ac.uns.ftn.backend.repository.AdventureRepository;
import rs.ac.uns.ftn.backend.repository.CottageRepository;
import rs.ac.uns.ftn.backend.repository.InstructorRepository;


import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class InstructorService {

    InstructorRepository ir;

    AdventureRepository ar;

    private List<String> possibleType = Arrays.asList("name","address", "numberOfRoom","numberOfBedPerRoom");

    public InstructorService(InstructorRepository ir2,AdventureRepository ar2){
        ir = ir2;
        ar= ar2;
    }

    @Async
    public CompletableFuture<List<InstructorDTO>> getAllInstructors(Integer pageNum , Integer pageSize) {

        log.info("GET ALL COTTAGES "+ Thread.currentThread().getName());

        Pageable singlePage = PageRequest.of(pageNum, pageSize);

        Page<Instructor> instructors = ir.findAll(singlePage);

        List<InstructorDTO> idto = instructors.stream().map(
                i->
                        new InstructorDTO( i.getId(),i.getName(),i.getEmail(),i.getAddress(),i.getDescription(),i.averageMarks() )
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(idto);

    }

    @Async
    public CompletableFuture<List<InstructorDTO>> getAllInstructors(Integer pageNum , Integer pageSize, String sortType , Boolean direction) {

        log.info("GET ALL INSTRUCTOR SORTED"+ Thread.currentThread().getName());

        if(!possibleType.contains(sortType)){
            return CompletableFuture.completedFuture(new ArrayList<>());
        }

        Pageable singlePage;

        if(direction){
            singlePage = PageRequest.of(pageNum, pageSize, Sort.by(sortType));
        }
        else {
            singlePage = PageRequest.of(pageNum, pageSize,Sort.by(sortType).descending());
        }

        Page<Instructor> instructors = ir.findAll(singlePage);

        List<InstructorDTO> idto = instructors.stream().map(
                i->
                        new InstructorDTO( i.getId(),i.getName(),i.getEmail(),i.getAddress(),i.getDescription(),i.averageMarks() )
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(idto);

    }

//    @Async
//    public CompletableFuture<InstructorProfileDTO> getOneInstructor(Long id) {
//
//        log.info("GET ONE INSTRUCTOR" + Thread.currentThread().getName());
//
//        Optional<Instructor> io = ir.findById(id);
//
//        Instructor i = io.get();
//
//        Set<Adventure> adv = ar.findAllAdventureByInstructor(i.getId());
//
//        InstructorProfileDTO ipdto = new InstructorProfileDTO(
//                i.getId(),i.getName(),i.getEmail(),i.getAddress(), i.getDescription(),i.averageMarks(),
//                ct.getNumberOfRoom(),ct.getNumberOfBedPerRoom(),ct.getRuleBehavior(), ct.getMoreInformation(), ct.getCottageImages(),
//                ct.getCottagePricelists(),ct.averageMarks(), ct.getCottageResevations() , ct.getCottageActions()
//        );
//
//        return CompletableFuture.completedFuture(ipdto);
//
//    }
}
