package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.CottageAction;

public interface CottageActionRepository extends JpaRepository<CottageAction,Long> {
}
