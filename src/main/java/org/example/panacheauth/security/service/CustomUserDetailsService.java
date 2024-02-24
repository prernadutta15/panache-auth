package org.example.panacheauth.security.service;

import org.example.panacheauth.models.User;
import org.example.panacheauth.repos.UserRepository;
import org.example.panacheauth.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;
    CustomUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if(userOptional.isEmpty())
        {
            throw new UsernameNotFoundException("User not found with email "+ username);
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(userOptional.get());

        return customUserDetails;
    }
}
