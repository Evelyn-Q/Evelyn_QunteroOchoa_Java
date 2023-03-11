package com.company.bookstore.repository;

import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthorId(Integer authorId);
    List<Book> books = new ArrayList<Book>();
    public default Book getBookById(Integer id) {
        List<Book> idMatch = this.books;
        Book resultBook = null;
        for (Book b : idMatch) {
            if (b.getId().equals(id)) {
                resultBook = b;
            }
        }
        return resultBook;
    }
}
