package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.backend.model.Boat;
@Repository
public interface BoatRepository  extends JpaRepository<Boat,Long> {
}
