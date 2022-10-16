package com.tuling.repositories;

/*
@author YG
@create 2022/10/15   22:47
*/

import com.tuling.pojo.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CustomerSpecificationsRepository extends PagingAndSortingRepository<Customer, Long>
, JpaSpecificationExecutor<Customer> {


}
