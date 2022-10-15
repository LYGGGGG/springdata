package com.tuling.repositories;

/*
@author YG
@create 2022/10/15   22:47
*/

import com.tuling.pojo.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
