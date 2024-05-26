package com.tedkvn.erp.service.user;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.entity.UserStatus;
import com.tedkvn.erp.rest.exception.BadRequest;
import com.tedkvn.erp.rest.exception.ResourceNotFoundException;
import com.tedkvn.erp.rest.request.SignInByPassword;
import com.tedkvn.erp.rest.request.SignUpRequest;
import com.tedkvn.erp.rest.response.JwtResponse;
import com.tedkvn.erp.security.JwtUtils;
import com.tedkvn.erp.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserService userService;

    @Autowired
    PasswordService passwordService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Long signUpUser(SignUpRequest request) {

        // New User
        User newUser = new User();

        Optional<String> email = Optional.ofNullable(request.getEmail());
        email.flatMap(e -> userService.findByEmail(email.get())).ifPresent(user -> {
            if (!user.getStatus().equals(UserStatus.INACTIVE)) {
                throw new BadRequest("This email has been used by another user!");
            }
        });
        email.ifPresent(newUser::setEmail);

        String username = request.getUsername();
        userService.findByUsername(username).ifPresent(user -> {
            if (!user.getStatus().equals(UserStatus.INACTIVE)) {
                throw new BadRequest("This username has been used by another user!");
            }
        });
        newUser.setUsername(username);

        String password = request.getPassword();
        newUser.setPassword(passwordService.encodePassword(password));

        newUser.setStatus(UserStatus.ACTIVE);
        newUser = userService.save(newUser);

        return newUser.getId();
    }

    @Override
    public JwtResponse signInByUsernameOrEmail(SignInByPassword request) {
        String usernameOrEmail = request.getUsernameOrEmail();
        String password = request.getPassword();

        Optional<User> user = userService.findByUsernameOrEmail(usernameOrEmail);

        if (user.isEmpty() || user.get().getStatus().equals(UserStatus.INACTIVE)) {
            throw new ResourceNotFoundException("User not found");
        }

        return signedJWTAuth(user.get(), password);
    }

    @Override
    public JwtResponse signedJWTAuth(User user, String password) {

        // authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getSub(), password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new JwtResponse(jwtUtils.generateJwtToken(user), userDetails);
    }
}
