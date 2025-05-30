package com.lms2025.librarymanagementsystem.controller;

import com.lms2025.librarymanagementsystem.model.book;
import com.lms2025.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public List<book> getAllBooks() {
        return bookService.getAllBooks();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public book addBook(@RequestBody book book) {
        return bookService.saveBook(book);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<book> updateBook(@PathVariable Long id, @RequestBody book book) {
        return bookService.getBookById(id)
                .map(existingBook -> {
                    book.setId(id);
                    return ResponseEntity.ok(bookService.saveBook(book));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(book -> {
                    bookService.deleteBook(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public List<book> searchBooks(@RequestParam String query) {
        return bookService.searchBooks(query);
    }
    
    @GetMapping("/available")
    public List<book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }
}