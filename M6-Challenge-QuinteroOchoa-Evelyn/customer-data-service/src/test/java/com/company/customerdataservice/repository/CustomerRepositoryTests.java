package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository customerRepo;

    @Before
    public void setUp() throws Exception {
        customerRepo.deleteAll();
    }

    @Test
    public void addCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setCompany("BigCo");
        customer.setPhone("123-456-7890");
        customer.setAddress1("1234 Green Lane");
        customer.setAddress2("1234 Green Lane");
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("United States");

        //Act...
        customer = customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void updateCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Jack");
        customer.setLastName("Smith");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setCompany("Apple");
        customer.setPhone("987-876-7364");
        customer.setAddress1("4321 Green Lane");
        customer.setAddress2("4321 Green Lane");
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("United States");

        customerRepo.save(customer);

        //Act...
        customer.setFirstName("UPDATED");

        customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void deleteCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("JoeSmith@gmail.com");
        customer.setCompany("BigCo");
        customer.setPhone("123-456-7890");
        customer.setAddress1("1234 Green Lane");
        customer.setAddress2("1234 Green Lane");
        customer.setCity("Los Angeles");
        customer.setState("California");
        customer.setPostalCode("12345");
        customer.setCountry("United States");

        customerRepo.save(customer);

        //Act...
        customerRepo.deleteById(customer.getId());

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());
        assertFalse(customer1.isPresent());
    }

    @Test
    public void findCustomerById() {
        //Arrange...
        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Smith");
        joe.setEmail("JoeSmith@gmail.com");
        joe.setCompany("BigCo");
        joe.setPhone("123-456-7890");
        joe.setAddress1("1234 Green Lane");
        joe.setAddress2("1234 Green Lane");
        joe.setCity("Los Angeles");
        joe.setState("California");
        joe.setPostalCode("12345");
        joe.setCountry("United States");

        customerRepo.save(joe);

        Customer bob = new Customer();
        bob.setFirstName("Bob");
        bob.setLastName("Smith");
        bob.setEmail("BobSmith@gmail.com");
        bob.setCompany("BigCo");
        bob.setPhone("123-456-7890");
        bob.setAddress1("1234 Green Lane");
        bob.setAddress2("1234 Green Lane");
        bob.setCity("Los Angeles");
        bob.setState("California");
        bob.setPostalCode("12345");
        bob.setCountry("United States");

        customerRepo.save(bob);

        //Act...
        customerRepo.findById(bob.getId());

        //Assert...
        Customer customer = customerRepo.findById(bob.getId()).get();
        assertEquals(bob, customer);
    }

    @Test
    public void findCustomersByState() {
        //Arrange...
        Customer frank = new Customer();
        frank.setFirstName("Joe");
        frank.setLastName("Smith");
        frank.setEmail("JoeSmith@gmail.com");
        frank.setCompany("BigCo");
        frank.setPhone("123-456-7890");
        frank.setAddress1("1234 Green Lane");
        frank.setAddress2("1234 Green Lane");
        frank.setCity("Los Angeles");
        frank.setState("California");
        frank.setPostalCode("12345");
        frank.setCountry("United States");

        customerRepo.save(frank);

        Customer alice = new Customer();
        alice.setFirstName("Joe");
        alice.setLastName("Smith");
        alice.setEmail("JoeSmith@gmail.com");
        alice.setCompany("BigCo");
        alice.setPhone("123-456-7890");
        alice.setAddress1("1234 Green Lane");
        alice.setAddress2("1234 Green Lane");
        alice.setCity("Los Angeles");
        alice.setState("Ohio");
        alice.setPostalCode("12345");
        alice.setCountry("United States");

        List<Customer> myList = new ArrayList<Customer>();
        myList.add(frank);

        //Act...
        customerRepo.findByState(frank.getState());

        //Assert...
        List<Customer> customerList = customerRepo.findByState(frank.getState());
        assertEquals(customerList, myList);
    }
}
