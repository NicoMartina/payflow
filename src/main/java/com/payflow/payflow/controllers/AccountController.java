package com.payflow.payflow.controllers;

import com.payflow.payflow.dtos.AccountRequestDTO;
import com.payflow.payflow.entities.Account;
import com.payflow.payflow.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody AccountRequestDTO request){
        Account savedAccount = accountService.createAccount(request.getOwnerName(), request.getInitialBalance());
        return ResponseEntity.ok(savedAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> fetchAccount(@PathVariable Long id){
        Account fetchedAccount = accountService.getAccount(id);
        return ResponseEntity.ok(fetchedAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> fetchAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}
