package com.example.two.services;

import com.example.two.dto.ResponseDto;
import com.example.two.exceptions.ProjectNotFoundException;
import com.example.two.exceptions.UserIdException;
import com.example.two.models.ERole;
import com.example.two.models.Role;
import com.example.two.models.User;
import com.example.two.repository.RoleRepository;
import com.example.two.repository.UserRepository;
import com.example.two.security.jwt.JwtUtils;
import com.example.two.security.request.LoginRequest;
import com.example.two.security.request.SignupRequest;
import com.example.two.security.response.JwtResponse;
import com.example.two.security.services.UserDetailsImpl;
import com.example.two.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@Override
	public ResponseDto register(SignupRequest signUpRequest) {
		String errorMessage = "User with this email has been found";
		try {
			if (Helper.notNull(userRepository.findByEmail(signUpRequest.getEmail()))) {
				throw new ProjectNotFoundException("User  already exists");
			}

			//		// Create new user's account
			User user = new User(signUpRequest.getUsername(),
					signUpRequest.getEmail(),
					encoder.encode(signUpRequest.getPassword()));

			Set<String> strRoles = signUpRequest.getRole();
			user.setRoles(buildFacadeForUserRolesSwitchStatement(strRoles));

			userRepository.save(user);

		} catch (Exception e) {
			throw new UserIdException(errorMessage);

		}

		return new ResponseDto("Successfully created", "think ");

	}

	@Override
	public ResponseEntity<JwtResponse> getAuthentication(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));

	}


	public Set<Role> buildFacadeForUserRolesSwitchStatement(Set<String> strRoles) {
		// Would call this a facade pattern and not a factory  because,
		// It is not creating anything new, so its behaviour falls under structural patterns
		//  has enum types for safety and calling dot notation, the method returns a ResponseDto. The role model stays isolated so i did not make a roledto
		// The main reason why i did this is to make the signup method more readable and learning about design patterns.

		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).get();
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR).get();
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
						roles.add(userRole);
				}
			});
		}
		return roles;
	}
}


