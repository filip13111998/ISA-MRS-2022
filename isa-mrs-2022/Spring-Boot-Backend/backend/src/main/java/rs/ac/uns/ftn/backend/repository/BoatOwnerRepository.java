package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.BoatMark;
import rs.ac.uns.ftn.backend.model.BoatOwner;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner,Long> {
}
