package com.ai.runai.Responses;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ai.runai.Models.FinanceInfo;

public class ExpenseResponse {
    private Long id;
    private Double amount;
    private String description;
    private LocalDateTime transTime;
    private LocalDate date;
    private String category; // Add category or any other fields as necessary

    // Constructor
    public ExpenseResponse(FinanceInfo financeInfo) {
        this.id = financeInfo.getId(); // Assuming you have an ID field
        this.amount = financeInfo.getAmount();
        this.description = financeInfo.getDescription();
        this.transTime = financeInfo.getTransTime();
        this.date = financeInfo.getDate();
        this.category = financeInfo.getCategory().getName(); // Adjust based on your category entity
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Double getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDateTime getTransTime() { return transTime; }
    public LocalDate getDate() { return date; }
    public String getCategory() { return category; }
}
