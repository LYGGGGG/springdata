package com.tuling;

/*
@author YG
@create 2022/10/16   11:39
*/

import com.tuling.config.SpringDataJPAConfig;
import com.tuling.pojo.Customer;
import com.tuling.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Stream;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringdataJpaPagingAndSortTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testPaging() {
        Page<Customer> all = repository.findAll(PageRequest.of(0, 2));
        System.out.println("============================");
        for (Customer customer : all) {
            System.out.println(customer);
        }
        System.out.println(all.getTotalElements());
        System.out.println(all.getTotalPages());
        Stream<Customer> customerStream = all.get();
    }

    @Test
    public void testSort() {
        Sort sort = Sort.by("custId").descending();
        Iterable<Customer> all = repository.findAll(sort);
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }
}
