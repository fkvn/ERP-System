package com.tedkvn.erp.rest.response;

import com.tedkvn.erp.security.UserDetailsImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Setter
@Getter
public class JwtResponse {

    private String access_token;
    private String type = "Bearer";
    private Long id;
    private Collection<String> authorities;
    private String username;
    private boolean isSuperAdmin;

    public JwtResponse(String accessToken) {
        this.access_token = accessToken;
    }

    public JwtResponse(String accessToken, UserDetailsImpl userDetails) {
        this.access_token = accessToken;
        this.id = userDetails.getId();
        this.authorities = getAuthorities(userDetails.getAuthorities());
        this.username = userDetails.getUsername();
        this.isSuperAdmin = userDetails.isSuperAdmin();
    }

    private Collection<String> getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

}
