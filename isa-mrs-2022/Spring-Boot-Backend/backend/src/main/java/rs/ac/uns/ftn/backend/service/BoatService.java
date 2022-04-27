package rs.ac.uns.ftn.backend.service;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.request.AdventureSearchSortDTO;
import rs.ac.uns.ftn.backend.dto.request.BoatSearchSortDTO;
import rs.ac.uns.ftn.backend.dto.request.CottageSearchSortDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.MyUserDTO;
import rs.ac.uns.ftn.backend.model.Adventure;
import rs.ac.uns.ftn.backend.model.Boat;
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.repository.BoatRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Slf4j
@Service
public class BoatService {

    private BoatRepository br;

    private List<String> possibleType = Arrays.asList("name","address", "type","lenght","engineNum","enginePower","maxSpeed");

    public BoatService(BoatRepository br) {
        this.br = br;
    }

    @Async
    public CompletableFuture<List<BoatDTO>> getAllBoats(Integer pageNum , Integer pageSize) {

        log.info("GET ALL COTTAGES "+ Thread.currentThread().getName());

        Pageable singlePage = PageRequest.of(pageNum, pageSize);

        Page<Boat> boats = br.findAll(singlePage);

        List<BoatDTO> bdto = boats.stream().map(
                b->
                        new BoatDTO(b.getId(),b.getName(), b.getBoatImages().stream().findFirst().orElse(null), b.getAdress(), b.getType(),b.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(bdto);
//        return null;
    }

    @Async
    public CompletableFuture<List<BoatDTO>> getAllBoats(Integer pageNum , Integer pageSize , String sortType , Boolean direction) {

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

        Page<Boat> boats = br.findAll(singlePage);

        List<BoatDTO> bdto = boats.stream().map(
                b->
                        new BoatDTO(b.getId(),b.getName(), b.getBoatImages().stream().findFirst().orElse(null), b.getAdress(), b.getType(),b.averageMarks())
        ).collect(Collectors.toList());

        return CompletableFuture.completedFuture(bdto);
//        return null;
    }

    @Async
    public CompletableFuture<BoatProfileDTO> getOneBoat(Long id){

        Optional<Boat> bto = br.findById(id);

        Boat bt = bto.get();

        BoatProfileDTO bpdto = new BoatProfileDTO(bt.getId(), bt.getName(),bt.getType(),bt.getLenght(),bt.getEngineNum(),bt.getEnginePower(),
                    bt.getMaxSpeed(),bt.getAdress(),bt.getLongitude(),bt.getLatitude(),bt.getCapacity(),bt.getDescription(),
                    bt.getNavigation(), bt.getRuleBehavior(),bt.getFishingEquipment(),bt.getMoreInformation(),bt.getBoatImages(),
                    bt.getBoatPricelists(),bt.averageMarks(), bt.getBoatResevations() , bt.getBoatActions()
                );


        return CompletableFuture.completedFuture(bpdto);


    }


    @Async
    public CompletableFuture<List<BoatDTO>> getAllBoatSearchSort(BoatSearchSortDTO bssdto, Integer pageNum , Integer pageSize, String sortType , Boolean direction) {

        BoatService.log.info("GET ALL COTTAGES BEST WAY "+ Thread.currentThread().getName());

        if(!possibleType.contains(sortType)) {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }

        List<BoatDTO> listBoatDTO = new ArrayList<>();
        Stream<Boat> stream;
        if(direction){
            stream = br.findAll(Sort.by(Sort.Direction.ASC, sortType)).stream();

        }
        else {
            stream = br.findAll(Sort.by(Sort.Direction.DESC, sortType)).stream();

        }

        listBoatDTO = stream.filter(e ->( this.checkBoat(e,bssdto)))
                .map(
                        b-> new BoatDTO(b.getId(),b.getName(), b.getBoatImages().stream().findFirst().orElse(null),
                                b.getAdress(), b.getType(),b.averageMarks())
                ).collect(Collectors.toList());



        PagedListHolder page = new PagedListHolder(listBoatDTO);

        page.setPageSize(pageSize); // number of items per page
        page.setPage(pageNum);      // set to first page

        return CompletableFuture.completedFuture(page.getPageList());

    }


    public Boolean checkBoat(Boat b, BoatSearchSortDTO bs){

        if(bs.getAddress() != null){
            if(!b.getAdress().equals(bs.getAddress())){
                return false;
            }

        }

        if(bs.getName() != null){
            if(!b.getName().equals(bs.getName())){
                return false;
            }

        }

        if(bs.getType() != null){
            if(!b.getType().equals(bs.getType())){
                return false;
            }

        }

        if(bs.getLenghtFrom() != null && bs.getLenghtTo() !=null){
            if(!(b.getLenght() > bs.getLenghtFrom() && b.getLenght() < bs.getLenghtTo())){
                return false;
            }

        } else  if(bs.getLenghtFrom() != null){
            if(!(b.getLenght() > bs.getLenghtFrom())){
                return false;
            }
        } else if (bs.getLenghtTo() != null){
            if(!(b.getLenght() < bs.getLenghtTo())){
                return false;
            }
        }

        if(bs.getEngineNumFrom() != null && bs.getEngineNumTo() !=null){
            if(!(b.getEngineNum() > bs.getEngineNumFrom() && b.getEngineNum() < bs.getEnginePowerTo())){
                return false;
            }

        }else  if(bs.getEngineNumFrom() != null){
            if(!(b.getEngineNum() > bs.getEngineNumFrom())){
                return false;
            }
        } else if (bs.getEngineNumTo() !=null){
            if(!(b.getEngineNum() < bs.getEnginePowerTo())){
                return false;
            }
        }

        if(bs.getEnginePowerFrom() != null && bs.getEnginePowerTo() !=null){
            if(!(b.getEnginePower() > bs.getEnginePowerFrom() && b.getEnginePower() < bs.getEnginePowerTo())){
                return false;
            }

        }else  if(bs.getEnginePowerFrom() != null){
            if(!(b.getEnginePower() > bs.getEnginePowerFrom())){
                return false;
            }
        } else if (bs.getEnginePowerTo() !=null){
            if(!(b.getEnginePower() < bs.getEnginePowerTo())){
                return false;
            }
        }

        if(bs.getMaxSpeedFrom() != null && bs.getMaxSpeedTo() !=null){
            if(!(b.getMaxSpeed() > bs.getMaxSpeedFrom() && b.getMaxSpeed() < bs.getMaxSpeedTo())){
                return false;
            }

        }else  if(bs.getMaxSpeedFrom() != null){
            if(!(b.getMaxSpeed() > bs.getMaxSpeedFrom())){
                return false;
            }
        } else if (bs.getMaxSpeedTo() !=null){
            if(!(b.getMaxSpeed() < bs.getMaxSpeedTo())){
                return false;
            }
        }

        if(bs.getAverageMark() != null){
            if( b.averageMarks() < bs.getAverageMark()){
                return false;
            }

        }

        return true;
    }

}
