package com.tuling.repositories;

/*
@author YG
@create 2022/10/15   22:47
*/

import com.tuling.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    //自定义查询
    @Query("FROM Customer where custName=:custName")
    Customer findCustomerBycustName(@Param("custName") String custName);

    @Transactional
    @Modifying
    @Query("update Customer c set c.custName=:name where c.custId=:id")
    int updateCustomer(@Param("id") Long id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query("delete from Customer c where c.custName=:name")
    int deleteCustomerByName(@Param("name") String name);

    //原生sql
    @Transactional
    @Modifying
    @Query(value = "select * from tb_customer where cust_name=:name", nativeQuery = true)
    List<Customer> queryCustomerByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "select * from tb_customer where id=:id", nativeQuery = true)
    List<Customer> queryCustomerById(@Param("id") Long id);

    List<Customer> findByCustAddress(String addr);

    @Transactional
    @Modifying
    int deleteByCustId(@Param("id")Long id);
}
