package org.example.panacheauth.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@SuppressWarnings("ALL")
@Setter @Getter @Entity
public class User extends BaseModel {
    private  String name;
    private String email;
    private String hashedPassword;
    /**
     * without eager we get whitelabel error
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    private boolean isEmailVerified;
}
