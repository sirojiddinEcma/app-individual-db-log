package uz.pdp.applog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.applog.entity.User;
import uz.pdp.applog.entity.enums.RoleName;
import uz.pdp.applog.payload.ApiResponse;
import uz.pdp.applog.payload.LoginDto;
import uz.pdp.applog.payload.UserDto;
import uz.pdp.applog.repository.RoleRepository;
import uz.pdp.applog.repository.UserRepository;
import uz.pdp.applog.security.JwtProvider;

import java.util.HashSet;

/**
 * BY SIROJIDDIN on 28.12.2020
 */


@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    public ApiResponse registerUser(UserDto userDto) {
        userRepository.save(new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                new HashSet<>(roleRepository.findAllByRoleName(RoleName.ROLE_USER))
        ));
        return new ApiResponse("OK", true);
    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));
            User user = (User) authenticate.getPrincipal();
            return new ApiResponse("OK", true, jwtProvider.generateToken(user));
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("Error", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

    }
}
