package rs.ac.uns.ftn.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.uns.ftn.backend.model.Role;
import rs.ac.uns.ftn.backend.repository.RoleRepository;
import rs.ac.uns.ftn.backend.service.RoleService;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role findById(Long id) {
    Optional<Role> auth = this.roleRepository.findById(id);
    return auth.get();
  }

  @Override
  public List<Role> findByName(String name) {
	List<Role> roles = this.roleRepository.findByName(name);
    return roles;
  }


}
