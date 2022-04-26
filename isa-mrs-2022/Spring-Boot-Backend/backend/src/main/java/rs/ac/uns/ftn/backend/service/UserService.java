package rs.ac.uns.ftn.backend.service;


import rs.ac.uns.ftn.backend.dto.response.UserRequest;
import rs.ac.uns.ftn.backend.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	User save(UserRequest userRequest);
}
