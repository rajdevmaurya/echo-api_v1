package com.echo.api.rest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.echo.api.model.User;
import com.echo.api.rest.dto.AuthResponse;
import com.echo.api.rest.dto.LoginRequest;
import com.echo.api.rest.dto.SignUpRequest;
import com.echo.api.security.CustomUserDetails;
import com.echo.api.security.SecurityConfig;
import com.echo.api.security.TokenProvider;
import com.echo.api.service.UserService;
import com.echo.api.user.DuplicatedUserInfoException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/authenticate")
	public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {

		String token = authenticateAndGetToken(loginRequest.username(), loginRequest.password());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		Long id = user.getId();
		String name = user.getName();
		String username = user.getUsername();
		String email = user.getEmail();
		String mobile = user.getMobileNumber();
		return new AuthResponse(token, id, username, name, email, mobile,roles);
	}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithUsername(signUpRequest.username())) {
            throw new DuplicatedUserInfoException(String.format("Username %s already been used", signUpRequest.username()));
        }
        if (userService.hasUserWithEmail(signUpRequest.email())) {
            throw new DuplicatedUserInfoException(String.format("Email %s already been used", signUpRequest.email()));
        }

		User user = userService.saveUser(this.mapSignUpRequestToUser(signUpRequest));
		Long id=user.getId();
        String token = authenticateAndGetToken(signUpRequest.username(), signUpRequest.password());
        List<String> userRoles = new ArrayList<>();
        userRoles.add(SecurityConfig.USER);
        return new AuthResponse(token,id,signUpRequest.username(),signUpRequest.name(),signUpRequest.email(),signUpRequest.mobileNumber(),userRoles);
    }

    private String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generate(authentication);
    }

	private User mapSignUpRequestToUser(SignUpRequest signUpRequest) {
		User user = new User();
		user.setUsername(signUpRequest.username());
		user.setPassword(passwordEncoder.encode(signUpRequest.password()));
		user.setName(signUpRequest.name());
		user.setEmail(signUpRequest.email());
		user.setMobileNumber(signUpRequest.mobileNumber());
		user.setRole(SecurityConfig.USER);
		return user;
	}
}
