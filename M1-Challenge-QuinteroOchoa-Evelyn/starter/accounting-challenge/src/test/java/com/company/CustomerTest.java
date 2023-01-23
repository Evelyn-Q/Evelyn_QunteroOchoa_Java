package com.company;

import  org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void shouldAddUpAllCharges() {
        List<String[]> customerCharges = Arrays.asList(
                new String[] {"10000","12-01-2021"},
                new String[] {"-5000","12-10-2022"},
                new String[] {"20000","12-22-2021"},
                new String[] {"-5000","12-23-2022"},
                new String[] {"0","12-24-2021"}
        );

        Customer testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setName("testCustomer");

        for (String[] customer : customerCharges) {
            String charge = customer[0];
            String date = customer[1];

            AccountRecord newCharge = new AccountRecord();
            newCharge.setCharge(Integer.parseInt(charge));
            newCharge.setChargeDate(date);
            testCustomer.getCharges().add(newCharge);
        }

        assertEquals(20000, testCustomer.getBalance());
    }


    @Test
    public void testToString() {
        List<String[]> customerData = Arrays.asList(
                new String[] {"10000","12-01-2021"},
                new String[] {"-5000","12-10-2022"},
                new String[] {"20000","12-22-2021"},
                new String[] {"-5000","12-23-2022"},
                new String[] {"0","12-24-2021"}
        );

        Customer testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setName("testCustomer");

        for (String[] customer : customerData) {
            String charge = customer[0];
            String date = customer[1];

            AccountRecord newCharge = new AccountRecord();
            newCharge.setCharge(Integer.parseInt(charge));
            newCharge.setChargeDate(date);
            testCustomer.getCharges().add(newCharge);
        }

        assertEquals("1 testCustomer 20000", testCustomer.toString());
    }
}