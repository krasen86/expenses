package se.expense.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.expense.model.Expense;
import se.expense.repository.ExpenseRepository;


import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping()
    Collection<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getExpenses(@PathVariable Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        return expense.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    ResponseEntity<?> createExpense(@Valid @RequestBody Expense expense) throws URISyntaxException {
        Expense addedExpense = expenseRepository.save(expense);
        return ResponseEntity.created(new URI("/api/expenses" + addedExpense.getId())).body(addedExpense);

    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense expense) throws URISyntaxException {
        Optional<Expense> expenseToUpdate = expenseRepository.findById(id);
        if (expenseToUpdate.isPresent()) {
            Expense updatedExpense = expenseRepository.save(expense);
            return ResponseEntity.ok().body(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteExpense(@PathVariable Long id)  {
        expenseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
