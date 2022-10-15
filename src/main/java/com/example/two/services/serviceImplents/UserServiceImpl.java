package com.example.two.services.serviceImplents;

import com.example.two.dto.ResponseDto;
import com.example.two.exceptions.ApiRequestException;
import com.example.two.exceptions.UserIdException;
import com.example.two.models.*;
import com.example.two.repository.CartRepository;
 import com.example.two.repository.UserRepository;
import com.example.two.security.jwt.JwtUtils;
import com.example.two.security.request.LoginRequest;
import com.example.two.security.request.SignupRequest;
import com.example.two.security.response.JwtResponse;
import com.example.two.security.services.UserDetailsImpl;
import com.example.two.security.services.UserDetailsServiceImpl;
import com.example.two.services.serviceInterfaces.ProductService;
import com.example.two.services.serviceInterfaces.RoleService;
import com.example.two.services.serviceInterfaces.UserService;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService    {

	@Autowired
	private final AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

 	@Autowired
	private final RoleService roleService;

	@Autowired
	private final ProductService productService;

	@Autowired
	private final CartRepository cartRepository;

	@Autowired
	private final PasswordEncoder encoder;
	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final UserDetailsServiceImpl userDetailsService;

	public UserServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, RoleService roleService, ProductService productService, CartRepository cartRepository,
						   PasswordEncoder encoder, UserRepository userRepository, UserDetailsServiceImpl userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.roleService = roleService;
		this.productService = productService;
		this.cartRepository = cartRepository;
		this.encoder = encoder;
		this.userRepository = userRepository;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public ResponseDto register(SignupRequest signUpRequest) {
		String errorMessage = "User with this email already exists";
		try {
			if (Helper.notNull(userRepository.findByEmail(signUpRequest.getEmail()))) {
				throw new UserIdException(errorMessage);
			}
			errorMessage = "User with this username already exists";
			if(userExistsDb(signUpRequest.getUsername())) {
				throw new UserIdException(errorMessage);
			}

			errorMessage = "Some role things";
			User user = new User(signUpRequest.getUsername(),
					signUpRequest.getEmail(),
					encoder.encode(signUpRequest.getPassword()));

			Set<String> strRoles = signUpRequest.getRole();
			user.setRoles(buildFacadeForUserRolesSwitchStatement(strRoles));
			errorMessage = "Some cart creating things";

			Cart cart = new Cart();
			cartRepository.save(cart);
			user.setCart(cart);

			userRepository.save(user);

		} catch (Exception e) {
			throw new UserIdException(errorMessage);

		}
		return new ResponseDto("Successfully created", "think ");

	}

	public boolean userExistsDb(String username) {
		return userRepository.findByUsername(username).isPresent();
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

	@Override
	public Product addToCart(Long prodId) {
			long id = 2;

			Product product = productService.findInDbProductById(id);
			User user = userRepository.findById(prodId).get();

			User updatedUserCart = user;

				System.out.println(user);
				System.out.println(user + " is already");
				System.out.println(user.getCart().getItems());
			System.out.println(product);
			updatedUserCart.getCart().addItem(product);

				System.out.println(user.getCart().getItems());
				userRepository.save(updatedUserCart);
			return product;
	}

	@Override
	public User getUserByUserId(Long  userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ApiRequestException("Could not find user with id " ));

			return user;
	}

	@Override
	public User findByUsername(String username) {

			User user = userRepository.findByUsername(username)
					.orElseThrow(() -> new ApiRequestException("Could not find user with id " ));
			return user;
	}

	@Override
	public List<User> fetchAllUser() {
		return userRepository.findAll();
	}


	public Set<Role> buildFacadeForUserRolesSwitchStatement(Set<String> strRoles) {
		// Would call this a facade pattern and not a factory  because,
		// It is not creating anything new, so its behaviour falls under structural patterns
		//  Enums for type safety and easy acces in switch statement with by  dot notation,
		//  the method returns a ResponseDto. The role model stays isolated so i did not make a roledto
		// The main reason why i did this is to make the signup method more readable and learning about design patterns.

		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleService.findByName(ERole.ROLE_USER).get();
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleService.findByName(ERole.ROLE_ADMIN).get();
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleService.findByName(ERole.ROLE_MODERATOR).get();
						roles.add(modRole);

						break;
					default:
						Role userRole = roleService.findByName(ERole.ROLE_USER).get();
						roles.add(userRole);
				}
			});
		}
		return roles;
	}


}


