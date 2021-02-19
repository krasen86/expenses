package se.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.expense.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(String name);

}
