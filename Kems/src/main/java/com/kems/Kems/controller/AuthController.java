package com.kems.Kems.controller;

import com.kems.Kems.entity.User;
import com.kems.Kems.entity.UserDto;
import com.kems.Kems.payload.JwtAuthRequest;
import com.kems.Kems.repository.UserRepository;
import com.kems.Kems.response.JwtAuthResponse;
import com.kems.Kems.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtTokenHelper;

    @Autowired
    private ModelMapper mapper;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) {
        String token = jwtTokenHelper.generateToken(request.getUsername());

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);

        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        // Debugging - Check if userOpt contains a valid User
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("User Found: " + user);  // Debugging print

            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());  // This line should work now
            userDto.setRole(user.getRole());

            response.setUser(userDto);
        } else {
            System.out.println("User not found!");  // Debugging print
        }

        return ResponseEntity.ok(response);
    }

}
