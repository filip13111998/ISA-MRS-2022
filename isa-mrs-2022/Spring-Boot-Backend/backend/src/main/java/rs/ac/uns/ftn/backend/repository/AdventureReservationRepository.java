package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.backend.model.Adventure;
import rs.ac.uns.ftn.backend.model.AdventureReservation;

public interface AdventureReservationRepository extends JpaRepository<AdventureReservation,Long> {

}
