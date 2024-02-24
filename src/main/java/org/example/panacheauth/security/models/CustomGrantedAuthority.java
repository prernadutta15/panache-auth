package org.example.panacheauth.security.models;

import lombok.NoArgsConstructor;
import org.example.panacheauth.models.Role;
import org.springframework.security.core.GrantedAuthority;
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
//    private Role role;
    private String authority;
    CustomGrantedAuthority(Role role)
    {
//        this.role = role;
        authority = role.getName();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
