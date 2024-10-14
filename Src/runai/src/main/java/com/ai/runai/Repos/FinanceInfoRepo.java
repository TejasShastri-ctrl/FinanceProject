package com.ai.runai.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.runai.Models.Category;
import com.ai.runai.Models.FinanceInfo;
import com.ai.runai.Models.User;

import java.util.*;
import java.util.List;


public interface FinanceInfoRepo extends JpaRepository<FinanceInfo, Long> {
    List<FinanceInfo> findByUserId(Long userId);
    List<FinanceInfo> findByCategory(Category category);
    List<FinanceInfo> findByUser(User user);
}
