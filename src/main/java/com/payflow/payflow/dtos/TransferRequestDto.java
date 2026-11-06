package com.payflow.payflow.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDto {
    private BigDecimal amount;
    private Long fromAccountId;
    private Long toAccountId;
    private String description;
}
