package rs.ac.uns.ftn.backend.service;

import java.util.List;

import rs.ac.uns.ftn.backend.dto.request.UserRequest;
import rs.ac.uns.ftn.backend.model.User;


public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	User save(UserRequest userRequest);
}
