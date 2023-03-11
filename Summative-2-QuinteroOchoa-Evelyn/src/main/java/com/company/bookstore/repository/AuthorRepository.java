package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> authors = new ArrayList<Author>();
    public default Author getAuthorById(Integer id) {
        List<Author> idMatch = this.authors;
        Author resultAuthor = null;
        for (Author a : idMatch) {
            if (a.getId().equals(id)) {
                resultAuthor = a;
            }
        }
        return resultAuthor;
    }
}
