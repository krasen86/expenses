package se.expense.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.expense.model.Category;
import se.expense.repository.CategoryRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }
    
    @GetMapping()
    Collection<Category> getCategories() {
        return categoryRepository.findAll();
    }
    @GetMapping("/{id}")
    ResponseEntity<?> getCategory(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping()
    ResponseEntity<?> createCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        Category addedCategory = categoryRepository.save(category);
        return ResponseEntity.created(new URI("/api/categories/" + addedCategory.getCategoryID())).body(addedCategory);

    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        Category addedCategory = categoryRepository.save(category);
        return ResponseEntity.ok().body(addedCategory);

    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> updateCategory(@PathVariable Long id)  {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
