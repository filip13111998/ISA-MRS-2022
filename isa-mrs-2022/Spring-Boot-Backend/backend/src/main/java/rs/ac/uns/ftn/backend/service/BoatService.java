package rs.ac.uns.ftn.backend.service;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.dto.response.BoatDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatProfileDTO;
import rs.ac.uns.ftn.backend.dto.response.CottageDTO;
import rs.ac.uns.ftn.backend.dto.response.MyUserDTO;
import rs.ac.uns.ftn.backend.model.Boat;
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.repository.BoatRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

}
