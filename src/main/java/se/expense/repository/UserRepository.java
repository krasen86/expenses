package se.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.expense.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
