package org.example.panacheauth.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LogoutRequestDto {
    private String token;
}
