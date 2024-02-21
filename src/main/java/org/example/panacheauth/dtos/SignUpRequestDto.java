package org.example.panacheauth.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
}
