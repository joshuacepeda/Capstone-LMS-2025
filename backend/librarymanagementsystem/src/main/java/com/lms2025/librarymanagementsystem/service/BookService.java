package com.lms2025.librarymanagementsystem.service;

import com.lms2025.librarymanagementsystem.model.book;
import com.lms2025.librarymanagementsystem.repository.bookrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    
    @Autowired
    private bookrepository bookRepository;
    
    public List<book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Optional<book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    public book saveBook(book book) {
        return bookRepository.save(book);
    }
    
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    public List<book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCase(query);
    }
    
    public List<book> getAvailableBooks() {
        return bookRepository.findByAvailable(true);
    }
}