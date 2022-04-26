package rs.ac.uns.ftn.backend.service;

import rs.ac.uns.ftn.backend.model.Role;


import java.util.List;

public interface RoleService {
	Role findById(Long id);
	List<Role> findByName(String name);
}
