package com.company.bookstore.respository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.JoinColumn;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorRepositoryTests {
    @Autowired
    BookRepository bookRepo;

    @Autowired
    AuthorRepository authorRepo;

    @Before
    public void setUp() throws Exception {
        bookRepo.deleteAll();
    }

    //Test Create Author
    @Test
    public void addAuthor() {
        //Arrange...
        Author author = new Author();
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        //Act...
        author = authorRepo.save(author);

        //Assert...
        Optional<Author> authorFound = authorRepo.findById(author.getId());

        assertEquals(authorFound.get(), author);
    }

    //Test get by id
    @Test
    public void findAuthorById() {
        //Arrange...
        Author author1 = new Author();
        author1.setFirstName("Joe");
        author1.setLastName("Smith");
        author1.setStreet("Ivy way");
        author1.setCity("Los Angeles");
        author1.setState("Ca");
        author1.setPostalCode("12345");
        author1.setPhone("123-456-7890");
        author1.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author1);

        Author author2 = new Author();
        author2.setFirstName("Joana");
        author2.setLastName("Smith");
        author2.setStreet("Ivy way");
        author2.setCity("Los Angeles");
        author2.setState("Ca");
        author2.setPostalCode("12345");
        author2.setPhone("123-456-7890");
        author2.setEmail("JoanaSmith@gmail.com");

        authorRepo.save(author2);

        //Act...
        authorRepo.findById(author1.getId());

        //Assert...
        Author foundAuthor = authorRepo.findById(author1.getId()).get();
        assertEquals(foundAuthor, author1);
    }

    //Test get all
    @Test
    public void getAllAuthors() {

        //Arrange...
        authorRepo.deleteAll();

        Author author1 = new Author();
        author1.setFirstName("Joe");
        author1.setLastName("Smith");
        author1.setStreet("Ivy way");
        author1.setCity("Los Angeles");
        author1.setState("Ca");
        author1.setPostalCode("12345");
        author1.setPhone("123-456-7890");
        author1.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author1);

        Author author2 = new Author();
        author2.setFirstName("Joana");
        author2.setLastName("Smith");
        author2.setStreet("Ivy way");
        author2.setCity("Los Angeles");
        author2.setState("Ca");
        author2.setPostalCode("12345");
        author2.setPhone("123-456-7890");
        author2.setEmail("JoanaSmith@gmail.com");

        authorRepo.save(author2);

        List<Author> aList = authorRepo.findAll();

        assertEquals(aList.size(), 2);

    }

    //Test Update Authors
    @Test
    public void updateAuthor() {
        //Arrange...
        Author author1 = new Author();
        author1.setFirstName("Joe");
        author1.setLastName("Smith");
        author1.setStreet("Ivy way");
        author1.setCity("Los Angeles");
        author1.setState("Ca");
        author1.setPostalCode("12345");
        author1.setPhone("123-456-7890");
        author1.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author1);

        //Act...
        author1.setCity("Berkeley");

        authorRepo.save(author1);

        //Assert...
        Optional<Author> author = authorRepo.findById(author1.getId());

        assertEquals(author.get(), author1);
    }

    //Test Delete Author
    @Test
    public void deleteAuthor() {
        //Arrange...
        Author author = new Author();
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        author = authorRepo.save(author);

        //Act...
        authorRepo.deleteById(author.getId());

        //Assert...
        Optional<Author> author1 = authorRepo.findById(author.getId());
        assertFalse(author1.isPresent());
    }

}
