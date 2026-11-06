package com.payflow.payflow.services;

import com.payflow.payflow.entities.Account;
import com.payflow.payflow.entities.EntryType;
import com.payflow.payflow.entities.LedgerEntry;
import com.payflow.payflow.entities.Transfer;
import com.payflow.payflow.repositories.AccountRepository;
import com.payflow.payflow.repositories.LedgerEntryRepository;
import com.payflow.payflow.repositories.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final LedgerEntryRepository ledgerEntryRepository;



    public Transfer executeTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount, String description){
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Account entered not found."));

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account doesnt exist"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

       fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
       toAccount.setBalance(toAccount.getBalance().add(amount));

       fromAccount = accountRepository.save(fromAccount);
       toAccount = accountRepository.save(toAccount);

       Transfer transfer = new Transfer();

       transfer.setAmount(amount);
       transfer.setDescription(description);
       transfer.setFromAccount(fromAccount);
       transfer.setToAccount(toAccount);

       transfer = transferRepository.save(transfer);

        LedgerEntry debitLedger = new LedgerEntry();

        debitLedger.setAmount(transfer.getAmount());
        debitLedger.setAccount(fromAccount);
        debitLedger.setType(EntryType.DEBIT);
        debitLedger.setTransfer(transfer);


        LedgerEntry creditLedger = new LedgerEntry();

        creditLedger.setAmount(transfer.getAmount());
        creditLedger.setAccount(toAccount);
        creditLedger.setTransfer(transfer);
        creditLedger.setType(EntryType.CREDIT);

        ledgerEntryRepository.save(debitLedger);
        ledgerEntryRepository.save(creditLedger);






    return transfer;
    }
}
