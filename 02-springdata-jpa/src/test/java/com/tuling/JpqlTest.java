package com.tuling;

/*
@author YG
@create 2022/10/16   13:26
*/

import com.tuling.config.SpringDataJPAConfig;
import com.tuling.pojo.Customer;
import com.tuling.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpqlTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testfindById() {
        Customer zzz = repository.findCustomerBycustName("zzz");
        System.out.println("======================================");
        System.out.println(zzz);
    }

    @Test
    public void testUpdateById() {
        repository.updateCustomer(8L, "哈哈");
    }

    @Test
    public void testDeleteByName() {
        repository.deleteCustomerByName("zzz");
    }

    @Test
    public void testFind() {
        List<Customer> customers = repository.queryCustomerByName("哈哈");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void testId() {
        List<Customer> customers = repository.queryCustomerById(9l);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindA() {
        List<Customer> customers = repository.findByCustAddress("北京");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void testFindB() {
        int i = repository.deleteByCustId(6l);
        System.out.println(i);
    }
}
