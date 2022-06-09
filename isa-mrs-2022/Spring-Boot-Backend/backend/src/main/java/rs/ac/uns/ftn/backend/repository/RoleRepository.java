package rs.ac.uns.ftn.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.backend.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);

}
