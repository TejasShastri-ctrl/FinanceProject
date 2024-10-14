package com.ai.runai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.ai.runai.DTO.ExpenseDTO;
import com.ai.runai.Exceptions.ResourceNotFoundException;
import com.ai.runai.Models.Category;
import com.ai.runai.Models.FinanceInfo;
import com.ai.runai.Responses.ExpenseResponse;
import com.ai.runai.Services.CategoryService;
import com.ai.runai.Services.FinanceService;

@RestController
@RequestMapping("/api/finance")
public class FinanceControl {

    @Autowired
    private FinanceService financeService;

    // ! NOT MAKING A NEW CONTROLLER
    @Autowired
    private CategoryService categoryService;

    // add expense
    @PostMapping("/addExpense/{userId}")
    public ResponseEntity<ExpenseResponse> addExpense(@PathVariable Long userId, @RequestBody ExpenseDTO expenseDTO)
            throws Exception {
        FinanceInfo financeInfo = financeService.addExpense(userId, expenseDTO); // Modify the service method to return
                                                                                 // FinanceInfo
        ExpenseResponse expenseResponse = new ExpenseResponse(financeInfo);
        return ResponseEntity.ok(expenseResponse); // Return the expense response
    }

    // create category
    @PostMapping("/createCat/{name}")
    public ResponseEntity<Category> createCat(@PathVariable String name) throws Exception {
        Category cat = categoryService.createCat(name);
        return new ResponseEntity<Category>(cat, HttpStatus.CREATED);
    }

    // get all categories
    @GetMapping("/allCats")
    public ResponseEntity<List<Category>> getAllCats() throws Exception {
        List<Category> allcats = categoryService.getAllCats();
        return new ResponseEntity<List<Category>>(allcats, HttpStatus.OK);
    }

    // get all user expenses
    @GetMapping("/getExpenseBy/{id}")
    public ResponseEntity<List<FinanceInfo>> getUserExpenses(@PathVariable("id") long id) throws Exception {
        List<FinanceInfo> expenses = financeService.getExpensesByUser(id);
        return new ResponseEntity<List<FinanceInfo>>(expenses, HttpStatus.OK);
    }

    // get expenses by categoryName
    @GetMapping("/getByCat/{categoryName}")
    public ResponseEntity<List<FinanceInfo>> getExpensesByCategory(@PathVariable String categoryName) {
        List<FinanceInfo> expenses = financeService.getAllExpensesInCategory(categoryName);
        return ResponseEntity.ok(expenses);
    }

    // get expense per category for a user
    @GetMapping("/user/{userId}/CatWise")
    public ResponseEntity<Map<String, Double>> getExpensesPerCategoryForUser(@PathVariable Long userId) {
        try {
            Map<String, Double> expensesPerCategory = financeService.getExpensesPerCategoryForUser(userId);
            return ResponseEntity.ok(expensesPerCategory);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // User not found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Other errors
        }
    }

    // get User's monthly expense(all cats included)
    @GetMapping("/user/{userId}/monthly")
    public ResponseEntity<Map<String, Map<String, Double>>> getExpensesPerCategoryPerMonth(
            @PathVariable Long userId) {
        try {
            Map<String, Map<String, Double>> expensesPerMonth = financeService
                    .getExpensesPerCategoryAndMonthForUser(userId);
            return new ResponseEntity<>(expensesPerMonth, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}