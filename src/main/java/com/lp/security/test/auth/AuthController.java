package com.lp.security.test.auth;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.security.test.dto.LoginRequest;
import com.lp.security.test.dto.SignupRequest;
import com.lp.security.test.dto.response.JwtResponse;
import com.lp.security.test.dto.response.MessageResponse;
import com.lp.security.test.entity.Roles;
import com.lp.security.test.entity.User;
import com.lp.security.test.entity.UserRole;
import com.lp.security.test.repository.UserRepository;
import com.lp.security.test.repository.UserRoleRepository;
import com.lp.security.test.util.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		User userDetails = (User) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Builder object of user 
		User user = User.builder().name(signUpRequest.getName()).email(signUpRequest.getEmail())
				.password(encoder.encode(signUpRequest.getPassword())).build();


		String strRoles = signUpRequest.getRole();
		UserRole roles = null;

		if (strRoles == null || strRoles.isEmpty()) {
			UserRole userRole = roleRepository.findByName(Roles.ROLE_USER.name())
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles = userRole;

		} else {
			switch (strRoles) {
			case "admin":
				UserRole adminRole = roleRepository.findByName(Roles.ROLE_ADMIN.name())
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles = adminRole;
				break;
			case "mod":
				UserRole modRole = roleRepository.findByName(Roles.ROLE_MODERATOR.name())
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles = modRole;
				break;
			default:

				UserRole userRole = roleRepository.findByName(Roles.ROLE_USER.name())
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles = userRole;
			}

		}

		user.setRole(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
