package com.tuling.repositories;

/*
@author YG
@create 2022/10/15   22:47
*/

import com.tuling.pojo.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {


}
