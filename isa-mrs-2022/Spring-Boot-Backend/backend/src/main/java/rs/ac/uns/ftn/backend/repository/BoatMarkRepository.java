package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.BoatMark;
import rs.ac.uns.ftn.backend.model.CottageMark;

public interface BoatMarkRepository extends JpaRepository<BoatMark,Long> {

}
