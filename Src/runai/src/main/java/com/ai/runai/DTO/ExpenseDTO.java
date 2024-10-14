package com.ai.runai.DTO;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExpenseDTO {
    private Double amount;
    private String description;
    private String catName;
    LocalDate date; //manually entered
}