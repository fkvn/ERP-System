package com.tedkvn.erp.rest.response;

import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.security.UserDetailsImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
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
    private Set<Company> companies;

    public JwtResponse(String accessToken) {
        this.access_token = accessToken;
    }

    public JwtResponse(String accessToken, UserDetailsImpl userDetails) {
        this.access_token = accessToken;
        this.id = userDetails.getId();
        this.authorities = getAuthorities(userDetails.getAuthorities());
        this.username = userDetails.getUsername();
        this.isSuperAdmin = userDetails.isSuperAdmin();
        this.companies = userDetails.getCompanies();
    }

    private Collection<String> getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        if (authorities != null) {
            return authorities.stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        } else {
            // Handle the case where authorities is null (e.g., return an empty list)
            return Collections.emptyList();
        }
    }

}
