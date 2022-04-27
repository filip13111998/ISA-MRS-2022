package rs.ac.uns.ftn.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.CottageSearchSortDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageProfileDTO;
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.repository.CottageRepository;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.log;

@Transactional
@Slf4j
@Service
public class CottageService {

    CottageRepository cr;

    private List<String> possibleType = Arrays.asList("name","address", "numberOfRoom","numberOfBedPerRoom");

    public CottageService(CottageRepository cr2){
        cr = cr2;
    }

    @Async
    public CompletableFuture<List<CottageDTO>> getAllCottages(Integer pageNum , Integer pageSize) {

        CottageService.log.info("GET ALL COTTAGES "+ Thread.currentThread().getName());

        Pageable singlePage = PageRequest.of(pageNum, pageSize);

        Page<Cottage> cottages = cr.findAll(singlePage);

        List<CottageDTO> cdto = cottages.stream().map(
                c->
                        new CottageDTO(c.getId(),c.getName(), c.getCottageImages().stream().findFirst().orElse(null), c.getAddress(), c.sumBedNumer(),c.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(cdto);

    }

    @Async
    public CompletableFuture<List<CottageDTO>> getAllCottages(Integer pageNum , Integer pageSize, String sortType , Boolean direction) {

        CottageService.log.info("GET ALL COTTAGES SORTED"+ Thread.currentThread().getName());

        if(!possibleType.contains(sortType)){
            return CompletableFuture.completedFuture(new ArrayList<>());
        }

        Pageable singlePage;

        if(direction){
            singlePage = PageRequest.of(pageNum, pageSize,Sort.by(sortType));
        }
        else {
            singlePage = PageRequest.of(pageNum, pageSize,Sort.by(sortType).descending());
        }

        Page<Cottage> cottages = cr.findAll(singlePage);

        List<CottageDTO> cdto = cottages.stream().map(
                c->
                        new CottageDTO(c.getId(),c.getName(), c.getCottageImages().stream().findFirst().orElse(null), c.getAddress(), c.sumBedNumer(),c.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(cdto);

    }

    @Async
    public CompletableFuture<CottageProfileDTO> getOneCottage(Long id) {

        Optional<Cottage> cto = cr.findById(id);

        Cottage ct = cto.get();

        CottageProfileDTO cpdto = new CottageProfileDTO(
                    ct.getId(),ct.getName(),ct.getAddress(),ct.getLongitude(), ct.getLatitude(), ct.getDescription(),
                    ct.getNumberOfRoom(),ct.getNumberOfBedPerRoom(),ct.getRuleBehavior(), ct.getMoreInformation(), ct.getCottageImages(),
                    ct.getCottagePricelists(),ct.averageMarks(), ct.getCottageResevations() , ct.getCottageActions()
                );

        return CompletableFuture.completedFuture(cpdto);



    }

    @Async
    public CompletableFuture<List<CottageDTO>>  getAllCottages(CottageSearchSortDTO sccdto, Integer pageNum, Integer pageSize, String type, Boolean direction) {

        CottageService.log.info("GET ALL COTTAGES SORTED"+ Thread.currentThread().getName());

        if(!possibleType.contains(type)){
            return CompletableFuture.completedFuture(new ArrayList<>());
        }

        Pageable singlePage;

        if(direction){
            singlePage = PageRequest.of(pageNum, pageSize,Sort.by(type));
        }
        else {
            singlePage = PageRequest.of(pageNum, pageSize,Sort.by(type).descending());
        }

        Page<Cottage> cottages = cr.findAllByParams(sccdto.getName(),sccdto.getAddress(),sccdto.getNumberOfRoom(),singlePage);

        List<CottageDTO> cdto = cottages.stream().map(
                c->
                        new CottageDTO(c.getId(),c.getName(), c.getCottageImages().stream().findFirst().orElse(null), c.getAddress(), c.sumBedNumer(),c.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(cdto);
    }

    @Async
    public CompletableFuture<List<CottageDTO>> getAllCottagesBestWay(CottageSearchSortDTO sccdto,Integer pageNum , Integer pageSize, String sortType , Boolean direction) {

        CottageService.log.info("GET ALL COTTAGES BEST WAY "+ Thread.currentThread().getName());

        if(!possibleType.contains(sortType)) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }

        List<CottageDTO> listCottageDTO = new ArrayList<>();
        Stream<Cottage> stream;
        if(direction){
            stream = cr.findAll(Sort.by(Sort.Direction.ASC, sortType)).stream();

        }
        else {
            stream = cr.findAll(Sort.by(Sort.Direction.DESC, sortType)).stream();

        }

        listCottageDTO = stream.filter(e ->( this.checkCottage(e,sccdto)))
                            .map(
                                    c-> new CottageDTO(c.getId(),c.getName(), c.getCottageImages().stream().findFirst().orElse(null),
                                    c.getAddress(), c.sumBedNumer(),c.averageMarks())
        ).collect(Collectors.toList());



        PagedListHolder page = new PagedListHolder(listCottageDTO);

        page.setPageSize(pageSize); // number of items per page
        page.setPage(pageNum);      // set to first page

        return CompletableFuture.completedFuture(page.getPageList());

    }


    public Boolean checkCottage(Cottage c,CottageSearchSortDTO sc){

        if(sc.getAddress() != null){
            if(!c.getAddress().equals(sc.getAddress())){
                return false;
            }

        }

        if(sc.getName() != null){
            if(!c.getName().equals(sc.getName())){
                return false;
            }

        }

        if(sc.getNumberOfRoom() != null){
            if(!c.getNumberOfRoom().equals(sc.getNumberOfRoom())){
                return false;
            }

        }

        if(sc.getAverageMark() != null){
            if( c.averageMarks() < sc.getAverageMark()){
                return false;
            }

        }

        return true;
    }
}
