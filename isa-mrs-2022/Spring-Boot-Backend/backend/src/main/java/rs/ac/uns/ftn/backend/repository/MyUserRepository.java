package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser,Long> {
}
