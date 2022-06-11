package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.DeleteRequest;

public interface DeleteRequestRepository extends JpaRepository<DeleteRequest,Long> {

}
