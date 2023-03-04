package com.company.customerdataservice.controller;
import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // Customer Repository for testing purposes
    @MockBean
    private CustomerRepository customerRepo;

    @Before
    public void setUp() {
        // Standard set up method, for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired
    }

    // Testing POST /customers
    @Test
    public void shouldReturnNewCustomerOnPostRequest() throws Exception {

        // ARRANGE
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

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(frank);

        // ACT
        mockMvc.perform(
                        post("/customers")                         // Perform the POST request
                                .content(inputJson)                          // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)     // Tell the server it's in JSON format
                )
                .andDo(print())                                              // Print results to console
                .andExpect(status().isCreated());                            // ASSERT (status code is 201)
    }

    // Testing PUT /customers/{id}
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

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
        frank.setId(2);

        customerRepo.save(frank);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(frank);

        mockMvc.perform(
                        put("/customers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /customers/{id}
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

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
        frank.setId(2);

        customerRepo.save(frank);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(frank);

        mockMvc.perform(delete("/customers/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing GET customers/{id}
    @Test
    public void shouldReturnCustomerById() throws Exception {

        Customer frank = new Customer();
        frank.setId(3);
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

        mockMvc.perform(get("/customers/3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing GET customers/state/{state}
    @Test
    public void shouldReturnCustomerByState() throws Exception {

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

        List<Customer> myList = new ArrayList<Customer>();
        myList.add(frank);

        mockMvc.perform(get("/customers/state/California"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
