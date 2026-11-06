package com.payflow.payflow.controllers;

import com.payflow.payflow.dtos.TransferRequestDto;
import com.payflow.payflow.entities.Transfer;
import com.payflow.payflow.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transfer> create(@RequestBody TransferRequestDto transfer){
        Transfer savedTransfer = transferService.executeTransfer(transfer.getFromAccountId(), transfer.getToAccountId(), transfer.getAmount(), transfer.getDescription());
        return ResponseEntity.ok(savedTransfer);
    }
}
