package com.tuling.test;

/*
@author YG
@create 2022/10/15   10:32
*/

import com.tuling.pojo.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {

    // Session工厂  Session:数据库会话  代码和数据库的一个桥梁
    private SessionFactory sf;

    @Before
    public void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();

        //2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void testC() {
        //通过session进行持久化操作
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustName("易二");
            customer.setCustAddress("上海");
            session.save(customer);

            transaction.commit();
        }
    }

    @Test
    public void testR() {
        //通过session进行持久化操作
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();

            Customer customer = session.find(Customer.class, 2L);
            System.out.println(customer);

            transaction.commit();
        }
    }

    @Test
    public void testU() {
        //通过session进行持久化操作
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustId(1L);
            customer.setCustAddress("北京");
            customer.setCustName("易一");
            session.saveOrUpdate(customer);

            transaction.commit();
        }
    }

    @Test
    public void testD() {
        //通过session进行持久化操作
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();

            Customer customer = new Customer();
            customer.setCustId(2L);
            session.remove(customer);

            transaction.commit();

        }
    }
}
