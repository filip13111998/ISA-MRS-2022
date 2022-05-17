package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.backend.model.AdventurePricelist;


@Repository
public interface AdventurePricelistRepository extends JpaRepository<AdventurePricelist, Long> {
    AdventurePricelist findByDescription(String description);
}
