package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RequestDto {
    private BigDecimal amount;
    private Integer period;
    private BigDecimal procent;
}
