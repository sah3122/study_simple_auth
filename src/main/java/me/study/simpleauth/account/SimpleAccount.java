package me.study.simpleauth.account;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class SimpleAccount implements Serializable {
    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @ElementCollection
    private Set<AccountRole> roles;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
