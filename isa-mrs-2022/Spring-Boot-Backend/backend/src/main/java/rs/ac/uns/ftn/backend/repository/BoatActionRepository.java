package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.BoatAction;
import rs.ac.uns.ftn.backend.model.CottageAction;

public interface BoatActionRepository  extends JpaRepository<BoatAction,Long> {
}
