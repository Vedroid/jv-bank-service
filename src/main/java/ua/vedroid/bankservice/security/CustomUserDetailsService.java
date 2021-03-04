package ua.vedroid.bankservice.security;

import static org.springframework.security.core.userdetails.User.builder;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.vedroid.bankservice.entity.User;
import ua.vedroid.bankservice.repository.UserRepository;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(username).orElseThrow(() ->
                new UsernameNotFoundException("User " + username + " could not be found"));
        return builder()
                .username(user.getPhoneNumber())
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(role -> role.getRoleName().name())
                        .toArray(String[]::new))
                .build();
    }
}
