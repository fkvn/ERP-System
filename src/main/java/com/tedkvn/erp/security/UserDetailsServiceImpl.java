package com.tedkvn.erp.security;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String query) throws UsernameNotFoundException {
        // query could be id, username, phone, or email
        Optional<User> user = userRepository.findById(Long.valueOf(query));
        if (user.isEmpty()) user = userRepository.findByUsername(query);
        if (user.isEmpty()) user = userRepository.findByEmail(query);
        if (user.isEmpty()) user = userRepository.findByPhone(query);

        if (user.isEmpty() || user.get().isDeleted())
            throw new UsernameNotFoundException("User not found");

        return new UserDetailsImpl(user.get());
    }
}