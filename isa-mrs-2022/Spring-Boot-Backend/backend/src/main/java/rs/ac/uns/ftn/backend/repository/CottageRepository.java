package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.backend.dto.request.CottageSearchSortDTO;
import rs.ac.uns.ftn.backend.model.Cottage;
@Repository
public interface CottageRepository extends JpaRepository<Cottage,Long> {

    @Query("SELECT c FROM Cottage c WHERE (:name is null or c.name = :name) and " +
            "(:address is null or c.address = :address) and " +
            "(:numberOfRoom is null or c.numberOfRoom = :numberOfRoom)")
    Page<Cottage> findAllByParams(@Param("name") String name,@Param("address") String adress,@Param("numberOfRoom") Long numberOfRoom, Pageable pageable);

}
