package me.study.simpleauth.account;


import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SimpleAccountDto {

    private String username;

    private String password;

    private AccountRole role = AccountRole.USER;

    public SimpleAccount toEntity() {
        return SimpleAccount.builder()
                .username(this.username)
                .password(this.password)
                .roles(Set.of(this.role))
                .build();
    }
}
