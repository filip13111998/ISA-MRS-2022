package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.AdventureSearchSortDTO;
import rs.ac.uns.ftn.backend.dto.request.BoatSearchSortDTO;
import rs.ac.uns.ftn.backend.dto.response.*;
import rs.ac.uns.ftn.backend.model.Adventure;
import rs.ac.uns.ftn.backend.model.Boat;
import rs.ac.uns.ftn.backend.repository.AdventureRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Transactional
@Service
public class AdventureService {

    private AdventureRepository ar;

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
                        new AdventureDTO(a.getId(),a.getName(), a.getAdventureImages().stream().findFirst().orElse(null), a.getAddress(), a.getMaxNumPerson(),a.averageMarks())
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
                        new AdventureDTO(a.getId(),a.getName(), a.getAdventureImages().stream().findFirst().orElse(null), a.getAddress(), a.getMaxNumPerson(),a.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(adto);

    }

    @Async
    public CompletableFuture<AdventureProfileDTO> getOneAdventure(Long id){

        Optional<Adventure> ado = ar.findById(id);

        Adventure ad = ado.get();

        InstructorDTO idto = new InstructorDTO(ad.getInstructor().getId(),ad.getInstructor().getName(),ad.getInstructor().getEmail(),ad.getInstructor().getDescription(),ad.getInstructor().getAddress(),ad.getInstructor().averageMarks());

        AdventureProfileDTO apdto = new AdventureProfileDTO(ad.getId(),ad.getName(),ad.getAddress(),ad.getLongitude(),ad.getLatitude(),
                ad.getDescription(),idto, ad.getMaxNumPerson() ,ad.getRuleBehavior() , ad.getMoreInformation(),
                ad.getCancellationConditions(), ad.getAdventureImages(), ad.getAdventurePricelists(),ad.averageMarks(),
                ad.getAdventureReservations().stream().map(a-> new AdventureReservationDTO(a.getId(),a.getReservationStart(),a.getReservationEnd(),a.getActive(),a.getPricelistItem())).collect(Collectors.toSet()) ,
                ad.getAdventureActions()
        );

        return CompletableFuture.completedFuture(apdto);

    }

    public CompletableFuture<List<AdventureDTO>> getAllAdventuresSearchSort(AdventureSearchSortDTO assdto, Integer pageNum, Integer pageSize, String type, Boolean direction) {
        AdventureService.log.info("GET ALL COTTAGES BEST WAY "+ Thread.currentThread().getName());

        if(!possibleType.contains(type)) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }

        List<AdventureDTO> listAdventureDTO = new ArrayList<>();
        Stream<Adventure> stream;
        if(direction){
            stream = ar.findAll(Sort.by(Sort.Direction.ASC, type)).stream();

        }
        else {
            stream = ar.findAll(Sort.by(Sort.Direction.DESC, type)).stream();

        }

        listAdventureDTO = stream.filter(e ->( this.checkAdventure(e,assdto)))
                .map(
                        a-> new AdventureDTO(a.getId(),a.getName(), a.getAdventureImages().stream().findFirst().orElse(null),
                                a.getAddress(), a.getMaxNumPerson(),a.averageMarks())
                ).collect(Collectors.toList());



        PagedListHolder page = new PagedListHolder(listAdventureDTO);

        page.setPageSize(pageSize); // number of items per page
        page.setPage(pageNum);      // set to first page

        return CompletableFuture.completedFuture(page.getPageList());

    }


    public Boolean checkAdventure(Adventure a, AdventureSearchSortDTO as){

        if(as.getAddress() != null){
            if(!a.getAddress().equals(as.getAddress())){
                return false;
            }

        }

        if(as.getName() != null){
            if(!a.getName().equals(as.getName())){
                return false;
            }

        }

        if(as.getMaxNumPersonFrom() != null && as.getMaxNumPersonTo() !=null){
            if(!(a.getMaxNumPerson() > as.getMaxNumPersonFrom() && a.getMaxNumPerson() < as.getMaxNumPersonTo())){
                return false;
            }

        }else  if(as.getMaxNumPersonFrom() != null){
            if(!(a.getMaxNumPerson() > as.getMaxNumPersonFrom())){
                return false;
            }
        } else if (as.getMaxNumPersonTo() !=null){
            if(!(a.getMaxNumPerson() < as.getMaxNumPersonTo())){
                return false;
            }
        }


        if(as.getAverageMark() != null){
            if( a.averageMarks() < as.getAverageMark()){
                return false;
            }

        }

        return true;
    }
}
