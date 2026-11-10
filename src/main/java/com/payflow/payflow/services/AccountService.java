package com.payflow.payflow.services;

import com.payflow.payflow.entities.Account;
import com.payflow.payflow.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createAccount(String ownerName, BigDecimal initialBalance){
        Account account = new Account();
        account.setOwnerName(ownerName);
        account.setBalance(initialBalance);

        return accountRepository.save(account);
    }

    public Account getAccount(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("ID not found"));
        return account;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }


}
