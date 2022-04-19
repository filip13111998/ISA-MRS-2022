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
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.repository.CottageRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

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

        log.info("GET ALL COTTAGES "+ Thread.currentThread().getName());

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

        log.info("GET ALL COTTAGES SORTED"+ Thread.currentThread().getName());

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
}
