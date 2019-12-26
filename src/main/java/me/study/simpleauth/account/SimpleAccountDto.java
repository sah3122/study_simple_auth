package me.study.simpleauth.account;


import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SimpleAccountDto {

    private String username;

    private String password;

    private String role = "USER";

    public SimpleAccount toEntity() {
        return SimpleAccount.builder()
                .username(this.username)
                .password(this.password)
                .role(this.role)
                .build();
    }
}
