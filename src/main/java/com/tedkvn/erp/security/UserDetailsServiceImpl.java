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
    public UserDetails loadUserByUsername(String sub) throws UsernameNotFoundException {
        // we use sub to identify users
        Optional<User> user = userRepository.findBySub(sub);

        if (user.isEmpty() || user.get().isDeleted())
            throw new UsernameNotFoundException("User not found");

        return new UserDetailsImpl(user.get());
    }
}