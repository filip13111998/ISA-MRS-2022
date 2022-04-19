package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.backend.model.Adventure;


import java.util.Set;

@Repository
public interface AdventureRepository  extends JpaRepository<Adventure,Long> {

//    @Query(value = "SELECT a FROM Adventure a " +
//                    "   WHERE instructorId==a.instructor.id and" +
//                    "         ( month(a.adventureReservations.reservationStart) == month(current_date) or " +
//                    "               month(a.adventureReservations.reservationEnd) == month(current_date)")
//    Set<Adventure> findAllAdventureByInstructor(Long instructorId);

}
