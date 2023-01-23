package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    private static List<String[]> customerData = Arrays.asList(
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );

    public static void main(String[] args) {
        List<Customer> customerList = new ArrayList<>();
        HashMap<String, Customer> customerMap = new HashMap<String, Customer>();

        for (String[] customer : customerData) {
            String id = customer[0];
            String name = customer[1];
            String charge = customer[2];
            String date = customer[3];

            if (customerMap.containsKey(id)) {
                AccountRecord updateCharge = new AccountRecord();
                updateCharge.setCharge(Integer.parseInt(charge));
                updateCharge.setChargeDate(date);
                customerMap.get(id).getCharges().add(updateCharge);

            } else {
                Customer newCustomer = new Customer();
                newCustomer.setId(Integer.parseInt(id));
                newCustomer.setName(name);

                AccountRecord newCharge = new AccountRecord();
                newCharge.setCharge(Integer.parseInt(charge));
                newCharge.setChargeDate(date);
                newCustomer.getCharges().add(newCharge);

                customerMap.put(id, newCustomer);
            }
        }

        for (Customer customer : customerMap.values()) {
            customerList.add(customer);
        }

        List<Customer> positiveAccounts = new ArrayList<>();
        List<Customer> negativeAccounts = new ArrayList<>();

        for (Customer customer : customerList) {
            int balance = customer.getBalance();

            if (balance > 0) {
                positiveAccounts.add(customer);
            } else {
                negativeAccounts.add(customer);
            }
        }

        System.out.println("Positive accounts:");
        for (Customer customer : positiveAccounts) {
            System.out.println(customer.toString());
        }

        System.out.println("Negative accounts:");
        for (Customer customer : negativeAccounts) {
            System.out.println(customer.toString());
        }
    }
}
