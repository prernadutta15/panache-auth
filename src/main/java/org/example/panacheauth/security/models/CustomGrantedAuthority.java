package org.example.panacheauth.security.models;

import org.example.panacheauth.models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {
    private Role role;
    CustomGrantedAuthority(Role role)
    {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return role.getName();
    }
}
