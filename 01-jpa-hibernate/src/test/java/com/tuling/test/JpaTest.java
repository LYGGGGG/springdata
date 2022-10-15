package com.tuling.test;

/*
@author YG
@create 2022/10/15   16:41
*/

import com.tuling.pojo.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    EntityManagerFactory factory;
    @Before
    public void befor(){
        factory = Persistence.createEntityManagerFactory("HibernateJpa");
    }

    @Test
    public void testC(){
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = new Customer();
        customer.setCustName("张三");

        entityManager.persist(customer);
        transaction.commit();
    }

    @Test
    public void testR(){
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 1l);
        System.out.println(customer);

        transaction.commit();
    }
}
