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
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpqlTest {
    @Autowired
    CustomerRepository repository;

    @Test
    public void testfindById(){
        Customer zzz = repository.findCustomerBycustName("zzz");
        System.out.println("======================================");
        System.out.println(zzz);
    }
}
