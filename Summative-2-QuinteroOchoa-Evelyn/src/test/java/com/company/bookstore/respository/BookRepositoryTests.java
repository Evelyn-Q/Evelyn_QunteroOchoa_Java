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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTests {
    @Autowired
    BookRepository bookRepo;

    @Autowired
    AuthorRepository authorRepo;

    @Autowired
    PublisherRepository publisherRepo;

    @Before
    public void setUp() throws Exception {
        bookRepo.deleteAll();
        authorRepo.deleteAll();
        publisherRepo.deleteAll();
    }

    //Test Create Book
    @Test
    public void addBook() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisher = publisherRepo.save(publisher);

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

        Book book = new Book();
        book.setIsbn("123456789");
        book.setPublishDate(LocalDate.of(2020, 1, 8));
        book.setAuthorId(author.getId());
        book.setTitle("Pride and Prejudice");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("20.15"));

        //Act...
        book = bookRepo.save(book);

        //Assert...
        Optional<Book> foundBook = bookRepo.findById(book.getId());

        assertEquals(foundBook.get(), book);
    }

    //Test get all
    @Test
    public void getAllBooks() {

        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisherRepo.save(publisher);

        Author author = new Author();
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author);

        Book book1 = new Book();
        book1.setIsbn("123456789");
        book1.setPublishDate(LocalDate.of(2020, 1, 8));
        book1.setAuthorId(author.getId());
        book1.setTitle("Pride and Prejudice");
        book1.setPublisherId(publisher.getId());
        book1.setPrice(new BigDecimal("20.15"));

        book1 = bookRepo.save(book1);

        Book book2 = new Book();
        book2.setIsbn("987654321");
        book2.setPublishDate(LocalDate.of(2020, 1, 8));
        book2.setAuthorId(author.getId());
        book2.setTitle("Pride and Prejudice and Zombies");
        book2.setPublisherId(publisher.getId());
        book2.setPrice(new BigDecimal("20.15"));

        book2 = bookRepo.save(book2);

        List<Book> aList = bookRepo.findAll();

        assertEquals(aList.size(), 2);

    }

    //Test get by id
    @Test
    public void findBookById() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisherRepo.save(publisher);

        Author author = new Author();
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author);

        Book book1 = new Book();
        book1.setIsbn("123456789");
        book1.setPublishDate(LocalDate.of(2020, 1, 8));
        book1.setAuthorId(author.getId());
        book1.setTitle("Pride and Prejudice");
        book1.setPublisherId(publisher.getId());
        book1.setPrice(new BigDecimal("20.15"));

        book1 = bookRepo.save(book1);

        Book book2 = new Book();
        book2.setIsbn("987654321");
        book2.setPublishDate(LocalDate.of(2020, 1, 8));
        book2.setAuthorId(author.getId());
        book2.setTitle("Pride and Prejudice and Zombies");
        book2.setPublisherId(publisher.getId());
        book2.setPrice(new BigDecimal("20.15"));

        book2 = bookRepo.save(book2);

        //Act...
        bookRepo.findById(book1.getId());

        //Assert...
        Book foundBook = bookRepo.findById(book1.getId()).get();
        assertEquals(foundBook, book1);
    }

    //Test Update Book
    @Test
    public void updateBook() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisherRepo.save(publisher);

        Author author = new Author();
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author);

        Book book = new Book();
        book.setIsbn("123456789");
        book.setPublishDate(LocalDate.of(2020, 1, 8));
        book.setAuthorId(author.getId());
        book.setTitle("Pride and Prejudice");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("20.15"));

        book = bookRepo.save(book);

        //Act...
        book.setTitle("Pride and Prejudice and Zombies");

        bookRepo.save(book);

        //Assert...
        Optional<Book> book1 = bookRepo.findById(book.getId());

        assertEquals(book1.get(), book);
    }

    //Test Delete Book
    @Test
    public void deleteBook() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisherRepo.save(publisher);

        Author author = new Author();
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author);

        Book book = new Book();
        book.setIsbn("123456789");
        book.setPublishDate(LocalDate.of(2020, 1, 8));
        book.setAuthorId(author.getId());
        book.setTitle("Pride and Prejudice");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("20.15"));

        book = bookRepo.save(book);

        //Act...
        bookRepo.deleteById(book.getId());

        //Assert...
        Optional<Book> book1 = bookRepo.findById(book.getId());
        assertFalse(book1.isPresent());
    }

    @Test
    @Transactional
    public void findBooksByAuthor() {
        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisherRepo.save(publisher);

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

        Book book1 = new Book();
        book1.setIsbn("123456789");
        book1.setPublishDate(LocalDate.of(2020, 1, 8));
        book1.setAuthorId(author1.getId());
        book1.setTitle("Pride and Prejudice");
        book1.setPublisherId(publisher.getId());
        book1.setPrice(new BigDecimal("20.15"));

        book1 = bookRepo.save(book1);

        Book book2 = new Book();
        book2.setIsbn("987654321");
        book2.setPublishDate(LocalDate.of(2020, 1, 8));
        book2.setAuthorId(author1.getId());
        book2.setTitle("Pride and Prejudice and Zombies");
        book2.setPublisherId(publisher.getId());
        book2.setPrice(new BigDecimal("20.15"));

        book2 = bookRepo.save(book2);

        Book book3 = new Book();
        book3.setIsbn("987654321");
        book3.setPublishDate(LocalDate.of(2020, 1, 8));
        book3.setAuthorId(author2.getId());
        book3.setTitle("Not Pride and Prejudice");
        book3.setPublisherId(publisher.getId());
        book3.setPrice(new BigDecimal("20.15"));

        book3 = bookRepo.save(book3);

        List<Book> author1List = bookRepo.findByAuthorId(author1.getId());
        List<Book> author2List = bookRepo.findByAuthorId(author2.getId());

        assertEquals(2, author1List.size());
        assertEquals(1, author2List.size());

    }
}
