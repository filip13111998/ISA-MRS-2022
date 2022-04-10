package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.Cottage;

public interface CottageRepository extends JpaRepository<Cottage,Long> {
}
