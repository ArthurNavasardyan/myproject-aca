package com.aca.myprojectaca.service;

import com.aca.myprojectaca.config.jwt.JwtUtils;
import com.aca.myprojectaca.dto.JwtResponce;
import com.aca.myprojectaca.dto.LoginRequest;
import com.aca.myprojectaca.dto.MessageResponce;
import com.aca.myprojectaca.dto.SignupRequest;
import com.aca.myprojectaca.entity.ERole;
import com.aca.myprojectaca.entity.Role;
import com.aca.myprojectaca.entity.User;
import com.aca.myprojectaca.repository.RoleRepository;
import com.aca.myprojectaca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;


    public JwtResponce loginUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Error,Username is not found"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtils.generateJwtToken(authentication);
        JwtResponce jwtResponce = new JwtResponce();
        jwtResponce.setAccessToken(jwtToken);
        jwtResponce.setMessageResponce(new MessageResponce("Login is successful"));
        return jwtResponce;
    }

    public JwtResponce registerUser(SignupRequest signupRequest) {

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRole(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error,Role User is not found"));
        roles.add(userRole);

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);


        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),signupRequest.getPassword(),user.getRoles());
        String jwtToken = jwtUtils.generateJwtToken(authentication);
        JwtResponce jwtResponce = new JwtResponce();
        jwtResponce.setAccessToken(jwtToken);
        jwtResponce.setMessageResponce(new MessageResponce("registration is successful"));
        return jwtResponce;

    }
}
