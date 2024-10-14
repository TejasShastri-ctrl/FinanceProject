package com.ai.runai.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    String description;

    Double amount;

    LocalDateTime transTime;

    private LocalDate date;


    @ManyToOne // Change to EAGER for testing
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference
    private Category category;

    @ManyToOne // Change to EAGER for testing
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
    

}
