package me.study.simpleauth.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleAccountRepository extends JpaRepository<SimpleAccount, Long> {

    SimpleAccount findByUsername(String username);
}
