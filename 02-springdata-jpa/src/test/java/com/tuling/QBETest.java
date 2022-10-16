package com.tuling;

/*
@author YG
@create 2022/10/16   13:26
*/

import com.tuling.config.SpringDataJPAConfig;
import com.tuling.pojo.Customer;
import com.tuling.repositories.CustomerQBERepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QBETest {
    @Autowired
    CustomerQBERepository repository;

    @Test
    public void test01() {
        Customer customer = new Customer();
//        customer.setCustAddress("China");
        customer.setCustName("y");
//        customer.setCustName("YYY");
//        customer.setCustId(7l);
        ExampleMatcher matching = ExampleMatcher.matching().withIgnoreCase("custName")
                .withStringMatcher(ExampleMatcher.StringMatcher.ENDING);
        Example<Customer> example = Example.of(customer,matching);
        Iterable<Customer> customers = repository.findAll(example);
        for (Customer customer1 : customers) {
            System.out.println(customer1);
        }
    }

    @Test
    public void test02() {
        Customer customer = new Customer();
    }
}
