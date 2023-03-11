package com.company.bookstore.respository;

import com.company.bookstore.models.Author;
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

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublisherRepositoryTests {
    @Autowired
    BookRepository bookRepo;

    @Autowired
    PublisherRepository publisherRepo;

    @Before
    public void setUp() throws Exception {
        bookRepo.deleteAll();
    }

    //Test Create Publisher
    @Test
    public void addPublisher() {
        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        //Act...
        publisher = publisherRepo.save(publisher);

        //Assert...
        Optional<Publisher> publisherFound = publisherRepo.findById(publisher.getId());

        assertEquals(publisherFound.get(), publisher);
    }

    //Test Get Publisher by ID
    @Test
    public void findPublisherById() {
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

        Publisher publisher2 = new Publisher();
        publisher2.setName("Joana");
        publisher2.setStreet("Ivy way");
        publisher2.setCity("Los Angeles");
        publisher2.setState("Ca");
        publisher2.setPostalCode("12345");
        publisher2.setPhone("123-456-7890");
        publisher2.setEmail("JoanaSmith@gmail.com");

        publisher = publisherRepo.save(publisher2);

        //Act...
        publisherRepo.findById(publisher.getId());

        //Assert...
        Publisher foundPublisher = publisherRepo.findById(publisher.getId()).get();
        assertEquals(foundPublisher, publisher);
    }

    //Test Get All Publishers
    @Test
    public void getAllPublishers() {

        //Arrange...
        publisherRepo.deleteAll();

        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisher = publisherRepo.save(publisher);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Joana");
        publisher2.setStreet("Ivy way");
        publisher2.setCity("Los Angeles");
        publisher2.setState("Ca");
        publisher2.setPostalCode("12345");
        publisher2.setPhone("123-456-7890");
        publisher2.setEmail("JoanaSmith@gmail.com");

        publisher = publisherRepo.save(publisher2);

        List<Publisher> myList = publisherRepo.findAll();

        assertEquals(myList.size(), 2);

    }

    //Test Update Publishers
    @Test
    public void updatePublisher() {
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

        //Act...
        publisher.setName("Joe2");

        publisherRepo.save(publisher);

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getId());

        assertEquals(publisher1.get(), publisher);
    }

    //Test Author Book
    @Test
    public void deletePublisher() {
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

        //Act...
        publisherRepo.deleteById(publisher.getId());

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getId());
        assertFalse(publisher1.isPresent());
    }
}
