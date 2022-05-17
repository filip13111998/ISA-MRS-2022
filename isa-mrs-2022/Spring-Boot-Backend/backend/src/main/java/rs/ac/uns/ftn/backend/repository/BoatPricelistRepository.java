package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.backend.model.Boat;
import rs.ac.uns.ftn.backend.model.BoatPricelist;
import rs.ac.uns.ftn.backend.model.CottagePricelist;

@Repository
public interface BoatPricelistRepository extends JpaRepository<BoatPricelist,Long> {
    BoatPricelist findByDescription(String description);
}
