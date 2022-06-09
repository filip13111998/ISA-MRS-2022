package rs.ac.uns.ftn.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.ftn.backend.dto.request.RegisterUserDTO;
import rs.ac.uns.ftn.backend.dto.request.ValidationAccountTokenDTO;
import rs.ac.uns.ftn.backend.dto.response.BoatReservationHistoryDTO;
import rs.ac.uns.ftn.backend.dto.response.JwtAuthenticationRequest;
import rs.ac.uns.ftn.backend.dto.response.UserRequest;
import rs.ac.uns.ftn.backend.dto.response.UserTokenState;
import rs.ac.uns.ftn.backend.exception.ResourceConflictException;
import rs.ac.uns.ftn.backend.model.User;
import rs.ac.uns.ftn.backend.service.MyUserService;
import rs.ac.uns.ftn.backend.service.UserService;
import rs.ac.uns.ftn.backend.util.TokenUtils;


import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private MyUserService mus;

	
	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
//		System.out.println(authenticationRequest.getPassword()+authenticationRequest.getUsername());
		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
//		System.out.println("Principal"+((User) authentication.getPrincipal()).getUsername() + " " +((User) authentication.getPrincipal()).getPassword());
		String jwt = tokenUtils.generateToken(user.getUsername(),user.getRolesToString());
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	// Endpoint za registraciju novog korisnika
	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {

		User existUser = this.userService.findByUsername(userRequest.getUsername());

		if (existUser != null) {
			throw new ResourceConflictException(userRequest.getId(), "Username already exists");
		}

		User user = this.userService.save(userRequest);

		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	// Endpoint za registraciju novog korisnika
	@PostMapping("/register")
	public CompletableFuture<ResponseEntity<Boolean>> register(@RequestBody RegisterUserDTO ru) {

		return  mus.registerUser(ru).thenApplyAsync(ResponseEntity::ok);

	}

	@GetMapping("/isValid/{us}")
	public CompletableFuture<ResponseEntity<ValidationAccountTokenDTO>> isValid(@PathVariable String us) {

		return  mus.isActivate(us).thenApplyAsync(ResponseEntity::ok);

	}

	@GetMapping("/validate/{us}")
	public CompletableFuture<ResponseEntity<Boolean>> validate(@PathVariable String us) {

		return  mus.validateAcc(us).thenApplyAsync(ResponseEntity::ok);

	}

}