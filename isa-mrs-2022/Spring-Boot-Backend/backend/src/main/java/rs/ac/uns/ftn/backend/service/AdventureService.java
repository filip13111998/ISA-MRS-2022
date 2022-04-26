package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.response.AdventureDTO;
import rs.ac.uns.ftn.backend.dto.response.AdventureProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.InstructorDTO;
import rs.ac.uns.ftn.backend.model.Adventure;
import rs.ac.uns.ftn.backend.repository.AdventureRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class AdventureService {

    AdventureRepository ar;

    private List<String> possibleType = Arrays.asList("name","address", "maxNumPerson");

    public AdventureService(AdventureRepository ar){
        this.ar = ar;
    }

    @Async
    public CompletableFuture<List<AdventureDTO>> getAllAdventures(Integer pageNum , Integer pageSize) {

        log.info("GET ALL COTTAGES "+ Thread.currentThread().getName());

        Pageable singlePage = PageRequest.of(pageNum, pageSize);

        Page<Adventure> adventures = ar.findAll(singlePage);

        List<AdventureDTO> adto = adventures.stream().map(
                a->
                        new AdventureDTO(a.getId(),a.getName(), a.getAdventureImages().stream().findFirst().orElse(null), a.getAdress(), a.getMaxNumPerson(),a.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(adto);

    }

    @Async
    public CompletableFuture<List<AdventureDTO>> getAllAdventures(Integer pageNum , Integer pageSize , String sortType , Boolean direction) {

        log.info("GET ALL COTTAGES "+ Thread.currentThread().getName());

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

        Page<Adventure> adventures = ar.findAll(singlePage);

        List<AdventureDTO> adto = adventures.stream().map(
                a->
                        new AdventureDTO(a.getId(),a.getName(), a.getAdventureImages().stream().findFirst().orElse(null), a.getAdress(), a.getMaxNumPerson(),a.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(adto);

    }

    @Async
    public CompletableFuture<AdventureProfileDTO> getOneAdventure(Long id){

        Optional<Adventure> ado = ar.findById(id);

        Adventure ad = ado.get();

        InstructorDTO idto = new InstructorDTO(ad.getInstructor().getId(),ad.getInstructor().getName(),ad.getInstructor().getEmail(),ad.getInstructor().getDescription(),ad.getInstructor().getAddress(),ad.getInstructor().averageMarks());

        AdventureProfileDTO apdto = new AdventureProfileDTO(ad.getId(),ad.getName(),ad.getAdress(),ad.getLongitude(),ad.getLatitude(),
                ad.getDescription(),idto, ad.getMaxNumPerson() ,ad.getRuleBehavior() , ad.getMoreInformation(),
                ad.getCancellationConditions(), ad.getAdventureImages(), ad.getAdventurePricelists(),ad.averageMarks(),
                ad.getAdventureReservations() , ad.getAdventureActions()
        );

        return CompletableFuture.completedFuture(apdto);

    }

}
