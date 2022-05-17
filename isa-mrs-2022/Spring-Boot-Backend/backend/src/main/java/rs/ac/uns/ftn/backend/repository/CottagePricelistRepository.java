package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.backend.model.CottagePricelist;
import rs.ac.uns.ftn.backend.model.MyUser;
@Repository
public interface CottagePricelistRepository extends JpaRepository<CottagePricelist, Long> {
    CottagePricelist findByDescription(String description);
}
