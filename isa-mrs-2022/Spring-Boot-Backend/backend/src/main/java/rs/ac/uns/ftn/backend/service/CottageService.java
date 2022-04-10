package rs.ac.uns.ftn.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.repository.CottageRepository;

@Service
public class CottageService {

    CottageRepository cr;

    @Autowired
    public CottageService(CottageRepository cr2){
        cr = cr2;
    }

    public Double averageCottageMarks(){
        Cottage ctg = cr.getById(1l);
        return ctg.averageMarks();
    }

}
