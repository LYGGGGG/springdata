package com.tuling.repositories;

/*
@author YG
@create 2022/10/15   22:47
*/

import com.tuling.pojo.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {

    //自定义查询
    @Query("FROM Customer where custName=:custName")
    Customer findCustomerBycustName(@Param("custName") String custName);


}
