package com.tedkvn.erp.security;

import com.tedkvn.erp.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public record UserDetailsImpl(User user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getCompanies().stream().flatMap(company -> company.getUserRoles().stream()
                .flatMap(userRole -> Stream.concat(
                        Stream.of(new SimpleGrantedAuthority(userRole.getRole().getName().name())),
                        userRole.getRole().getAuthorities().stream()
                                .map(authority -> new SimpleGrantedAuthority(
                                        authority.getName().name()))))).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !user.isDeleted();
    }

    public Long getId() {
        return user.getId();
    }

    // Implement other UserDetails methods (isAccountNonExpired, etc.) based on your logic

    // Additional methods to access user information or company details if needed
}