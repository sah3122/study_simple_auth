package me.study.simpleauth.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SimpleAccountService implements UserDetailsService {

    private final SimpleAccountRepository simpleAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SimpleAccount account = simpleAccountRepository.findByUsername(username);

        if (account == null)
            throw new UsernameNotFoundException(username);

        return new SimpleAccountUser(account);
    }

    public SimpleAccount createAccount(SimpleAccountDto simpleAccountDto) {
        SimpleAccount simpleAccount = simpleAccountDto.toEntity();
        simpleAccount.encodePassword(passwordEncoder);
        SimpleAccount newAccount = simpleAccountRepository.save(simpleAccount);
        return newAccount;
    }

    public SimpleAccount authenticate(SimpleAccountDto simpleAccountDto) {
        SimpleAccount account = simpleAccountDto.toEntity();
        UserDetails userDetails = loadUserByUsername(account.getUsername());
        return null;
    }
}
