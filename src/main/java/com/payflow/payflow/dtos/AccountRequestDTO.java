package com.payflow.payflow.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequestDTO {
    private String ownerName;
    private BigDecimal initialBalance;
}
