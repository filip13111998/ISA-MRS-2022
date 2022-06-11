package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.BoatComplaint;
import rs.ac.uns.ftn.backend.model.BoatMark;
import rs.ac.uns.ftn.backend.model.CottageMark;

public interface BoatComplaintRepository extends JpaRepository<BoatComplaint,Long> {

}
