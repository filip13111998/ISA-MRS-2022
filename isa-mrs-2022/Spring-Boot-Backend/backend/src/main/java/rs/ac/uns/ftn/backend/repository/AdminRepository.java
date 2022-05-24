package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.uns.ftn.backend.model.Administrator;
import rs.ac.uns.ftn.backend.model.MyUser;

@Repository
public interface AdminRepository extends JpaRepository<Administrator,Long> {
    Administrator findByUsername(String username);
}
