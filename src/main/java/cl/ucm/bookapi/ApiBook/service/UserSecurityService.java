package cl.ucm.bookapi.ApiBook.service;


import cl.ucm.bookapi.ApiBook.entities.UserEntity;
import cl.ucm.bookapi.ApiBook.repository.UserRepository;
import cl.ucm.bookapi.ApiBook.repository.UserRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserRolRepository userRolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        List<String> roles = userRolRepository.getRolesByUsername(username);
        return User.builder()
                .username(username)
                .password(userEntity.getPassword())
                .disabled(false)
                .accountLocked(false)
                .authorities(grantedAuthorities(roles))
                .build();
    }

    private List<GrantedAuthority> grantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }
}
