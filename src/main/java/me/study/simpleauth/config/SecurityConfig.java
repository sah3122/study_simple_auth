package me.study.simpleauth.config;

import lombok.RequiredArgsConstructor;
import me.study.simpleauth.account.SimpleAccount;
import me.study.simpleauth.account.SimpleAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SimpleAccountService simpleAccountService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder.encode("password")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder.encode("password")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("password")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/login").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")

                .permitAll();
        http.userDetailsService(simpleAccountService);
        http.csrf().disable();
        http.httpBasic();
        http.headers().frameOptions().disable();
    }
}
