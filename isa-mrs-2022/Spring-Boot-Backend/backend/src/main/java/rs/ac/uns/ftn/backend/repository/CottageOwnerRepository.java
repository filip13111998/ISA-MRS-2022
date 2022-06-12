package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.BoatOwner;
import rs.ac.uns.ftn.backend.model.CottageOwner;

public interface CottageOwnerRepository extends JpaRepository<CottageOwner,Long> {
}
