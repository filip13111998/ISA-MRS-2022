package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.CottageComplaint;
import rs.ac.uns.ftn.backend.model.CottageMark;

public interface CottageMarkRepository extends JpaRepository<CottageMark,Long> {

}
