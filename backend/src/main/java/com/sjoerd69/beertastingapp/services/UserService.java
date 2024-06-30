package com.sjoerd69.beertastingapp.services;

import com.sjoerd69.beertastingapp.config.JWTUtil;
import com.sjoerd69.beertastingapp.dao.UserRepository;
import com.sjoerd69.beertastingapp.models.CustomUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userDAO;
    private final JWTUtil jwtUtil;

    public UserService(UserRepository userDAO, JWTUtil jwtUtil) {
        this.userDAO = userDAO;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUser customUser = userDAO.findByEmail(email);

        if (customUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(
                email,
                customUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public boolean authorizeAdmin(String bearerToken) {
        String email = jwtUtil.validateTokenAndRetrieveSubject(bearerToken.substring(7));
        CustomUser user = userDAO.findByEmail(email);

        if (user == null) {
            return false;
        }

        return user.getRole().equals("ROLE_ADMIN");
    }
}
