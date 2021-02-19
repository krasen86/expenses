package se.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.expense.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
