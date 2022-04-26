package rs.ac.uns.ftn.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rs.ac.uns.ftn.backend.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);
}
