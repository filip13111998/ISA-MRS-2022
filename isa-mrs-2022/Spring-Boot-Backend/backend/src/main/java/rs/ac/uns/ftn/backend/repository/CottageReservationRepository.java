package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.backend.model.Cottage;
import rs.ac.uns.ftn.backend.model.CottageResevation;

@Repository
public interface CottageReservationRepository extends JpaRepository<CottageResevation,Long> {
}
