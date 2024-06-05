package com.tedkvn.erp.security;

import com.tedkvn.erp.entity.User;
import com.tedkvn.erp.entity.UserStatus;
import com.tedkvn.erp.entity.organization.Company;
import com.tedkvn.erp.entity.privilege.Role;
import com.tedkvn.erp.entity.privilege.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private final User user;
    private final Long companyId;


    public UserDetailsImpl(User user, Long companyId) {
        this.user = user;
        this.companyId = companyId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // as default, we don't check for the userAuthorities when they sign in.
        // we only check when they select a specific company
        if (companyId == null) return Collections.emptyList();
        else {
            Set<UserRole> userRolesForCompany = user.getUserRoles().stream().filter(role ->
                            (role.getStore() != null &&
                                    role.getStore().getCompany().getId().equals(companyId)) ||
                                    (role.getWarehouse() != null &&
                                            role.getWarehouse().getCompany().getId().equals(companyId)))
                    .collect(Collectors.toSet());

            // 2. Extract and Group Authorities with distinct roles/authorities per store/warehouse
            return userRolesForCompany.stream().flatMap(userRole -> {
                Set<GrantedAuthority> authorities = new HashSet<>();
                Role role = userRole.getRole();

                // Generate a unique prefix for each store or warehouse ID
                String prefix = getStoreWarehousePrefix(userRole);

                // Add role with prefix
                authorities.add(new SimpleGrantedAuthority(prefix + role.getName().name()));

                // Add authorities with prefix
                authorities.addAll(role.getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(
                                prefix + authority.getName().name())).collect(Collectors.toSet()));

                return authorities.stream();
            }).collect(Collectors.toSet());
        }
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

    private String getStoreWarehousePrefix(UserRole role) {
        if (role.getStore() != null) {
            return "STORE_" + role.getStore().getId() + ": ";
        } else if (role.getWarehouse() != null) {
            return "WAREHOUSE_" + role.getWarehouse().getId() + ": ";
        } else {
            return ""; // Handle cases where neither store nor warehouse is assigned
        }
    }

    public Long getId() {
        return user.getId();
    }

    public Set<Company> getCompanies() {
        return user.getCompanies();
    }

    public boolean isSuperAdmin() {return user.isSuperAdmin();}

}