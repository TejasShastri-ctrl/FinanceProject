package com.ai.runai.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


import com.ai.runai.Models.FinanceInfo;
import com.ai.runai.Models.User;
import com.ai.runai.Repos.CatRepo;
import com.ai.runai.Repos.FinanceInfoRepo;
import com.ai.runai.Repos.UserRepo;
import com.ai.runai.Models.Category;
import com.ai.runai.DTO.ExpenseDTO; // Update this based on your actual package structure
import com.ai.runai.Exceptions.ResourceNotFoundException; // Update this based on your actual package structure

@Service
public class FinanceService {

    @Autowired
    private FinanceInfoRepo financeInfoRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private CatRepo categoryRepository;

    public FinanceInfo addExpense(Long userId, ExpenseDTO expenseDTO) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Category category = categoryRepository.findByName(expenseDTO.getCatName())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    
        FinanceInfo financeInfo = new FinanceInfo();
        financeInfo.setAmount(expenseDTO.getAmount());
        financeInfo.setDescription(expenseDTO.getDescription());
        financeInfo.setTransTime(LocalDateTime.now()); // Transaction noting date
        financeInfo.setDate(expenseDTO.getDate());
        financeInfo.setUser(user);
        financeInfo.setCategory(category);
        return financeInfoRepository.save(financeInfo); // Return the saved FinanceInfo object
    }
    

    // Per User per X month
    public List<FinanceInfo> getExpensePerMonth(Long userId, LocalDate date) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        List<FinanceInfo> expenses = financeInfoRepository.findByUserId(userId).stream()
                .filter(expense -> expense.getDate().getMonthValue() == date.getMonthValue() // Compare month values as integers
                        && expense.getDate().getYear() == date.getYear()) // Compare the year
                .collect(Collectors.toList());
        return expenses;
    }

    public List<FinanceInfo> getExpensesByUser(Long id) throws Exception {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return financeInfoRepository.findByUserId(id);
    }

    public List<FinanceInfo> getAllExpensesInCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        List<FinanceInfo> expenses = financeInfoRepository.findByCategory(category);
        return expenses;
    }

    public Map<String, Double> getExpensesPerCategoryForUser(Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<FinanceInfo> financeInfos = financeInfoRepository.findByUser(user);
        Map<String, Double> expensesPerCategory = financeInfos.stream()
                .collect(Collectors.groupingBy(
                        financeInfo -> financeInfo.getCategory().getName(),  // Group by category name
                        Collectors.summingDouble(FinanceInfo::getAmount)     // Sum the amounts
                ));
        return expensesPerCategory;
    }

    public Map<String, Map<String, Double>> getExpensesPerCategoryAndMonthForUser(Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        List<FinanceInfo> financeInfos = financeInfoRepository.findByUser(user);
    
        // Group by category name and month
        Map<String, Map<String, Double>> expensesPerCategoryAndMonth = financeInfos.stream()
                .collect(Collectors.groupingBy(
                        financeInfo -> financeInfo.getCategory().getName(),    // First group by category name
                        Collectors.groupingBy(
                                financeInfo -> financeInfo.getDate().getMonth().toString(), // Then group by month
                                Collectors.summingDouble(FinanceInfo::getAmount)                // Sum the amounts
                        )
                ));
    
        return expensesPerCategoryAndMonth;
    }
}
