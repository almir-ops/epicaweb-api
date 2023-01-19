package br.com.epicaweb.controller;

import br.com.epicaweb.domain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.epicaweb.domain.model.UserModel;
import br.com.epicaweb.security.JwtTokenUtil;
import br.com.epicaweb.ws.request.JwtRequest;
import br.com.epicaweb.ws.response.JwtResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@CrossOrigin(origins = "*")
@SpringBootApplication
@RestController
@RequestMapping("/api")
public class AuthserverApplication {

	@Autowired
	private AuthenticationManager authenticationManager;
    
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;
	
	@PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody String request) throws Exception {

		JwtRequest jwtRequest = userService.decode(request);

		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}
		catch(DisabledException e)
		{
//			return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
			return  new ResponseEntity<>("Usuario inativo.",HttpStatus.UNAUTHORIZED);
		}
		catch(BadCredentialsException e)
		{
			return  new ResponseEntity<>("Credencial Inválida.",HttpStatus.BAD_REQUEST);
		}
		
		
		final UserModel userDetails = userService.  loadUserByUsername(jwtRequest.getUsername());
//      userDetails.setUsername(authenticationRequest.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));		
	}
	 
    private void authenticate(String username, String password) throws Exception {
		//try {			
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));				
				/*
		} catch (DisabledException e) {
//throw new Exception("USER_DISABLED", e);
			return e.
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//throw new Exception("INVALID_CREDENTIALS", e);
		}
		*/
	}

}