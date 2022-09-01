package com.be.electroniccomponentstore.security;

import com.be.electroniccomponentstore.model.entity.Account;
import com.be.electroniccomponentstore.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.accountRepository.findAccountByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Not found username"));
        return UserPrinciple.createUser(account);
    }
}
