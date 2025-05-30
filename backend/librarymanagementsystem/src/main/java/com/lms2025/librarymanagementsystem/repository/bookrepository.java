package com.lms2025.librarymanagementsystem.repository;

import com.lms2025.librarymanagementsystem.model.book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface bookrepository extends JpaRepository<book, Long> {
    List<book> findByTitleContainingIgnoreCase(String title);
    List<book> findByAuthorContainingIgnoreCase(String author);
    List<book> findByGenreIgnoreCase(String genre);
    List<book> findByAvailable(boolean available);
}