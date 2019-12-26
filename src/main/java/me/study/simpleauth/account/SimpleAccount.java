package me.study.simpleauth.account;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class SimpleAccount implements Serializable {
    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String role;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
