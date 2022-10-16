package com.tuling;

/*
@author YG
@create 2022/10/15   22:48
*/

import com.tuling.config.SpringDataJPAConfig;
import com.tuling.pojo.Customer;
import com.tuling.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@ContextConfiguration(locations = "/spring.xml")
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringdataJpaTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testR() {
        Optional<Customer> byId = repository.findById(1L);
        System.out.println(byId.get());
    }

    @Test
    public void testC() {
        Customer customer = new Customer();
        customer.setCustName("YYY");
        customer.setCustAddress("China");

        System.out.println(repository.save(customer));
    }

    @Test
    public void testD() {
        Customer customer = new Customer();
        customer.setCustId(5L);
        repository.delete(customer);
    }

    @Test
    public void testSaveAll() {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();

        customer1.setCustName("ZZ");
        customer2.setCustName("YY");
        customer3.setCustName("XX");

        List list = new ArrayList<Customer>();
        list.add(customer1);
        list.add(customer2);
        list.add(customer3);
        repository.saveAll(list);
    }

    @Test
    public void testFinaAll() {
        List list = new ArrayList();
        list.add(1l);
        list.add(3l);
        list.add(8l);
        list.add(2l);
        Iterable allById = repository.findAllById(list);

        System.out.println("=======================================");
        System.out.println(allById);
    }


    @Test
    public void testCount() {
        long count = repository.count();
        System.out.println(count);
    }

}
