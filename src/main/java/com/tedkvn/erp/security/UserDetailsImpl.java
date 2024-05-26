package com.tedkvn.erp.security;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.entity.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

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
        return user.getPassword();
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
        return !user.getStatus().equals(UserStatus.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !user.getStatus().equals(UserStatus.INACTIVE);
    }

    public Long getId() {
        return user.getId();
    }

    public boolean isSuperAdmin() {return user.isSuperAdmin();}

    // Implement other UserDetails methods (isAccountNonExpired, etc.) based on your logic

    // Additional methods to access user information or company details if needed
}