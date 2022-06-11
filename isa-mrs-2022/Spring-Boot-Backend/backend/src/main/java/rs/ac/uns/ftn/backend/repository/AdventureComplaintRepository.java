package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.AdventureComplaint;
import rs.ac.uns.ftn.backend.model.CottageMark;

public interface AdventureComplaintRepository extends JpaRepository<AdventureComplaint,Long> {

}
