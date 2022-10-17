package com.tuling;

/*
@author YG
@create 2022/10/16   13:26
*/

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tuling.config.SpringDataJPAConfig;
import com.tuling.pojo.Customer;
import com.tuling.pojo.QCustomer;
import com.tuling.repositories.CustomerQueryDSLRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryDSLTest {
    @Autowired
    CustomerQueryDSLRepository repository;

    @Test
    public void test01() {
        QCustomer customer = QCustomer.customer;
        BooleanExpression eq = customer.custId.eq(8l);
        Optional<Customer> one = repository.findOne(eq);
        System.out.println(one);
    }

    @Test
    public void test02() {
        QCustomer customer = QCustomer.customer;
        //用 and 拼接
        BooleanExpression and = customer.custId.gt(4L).
                and(customer.custName.in("张三", "ZZ", "XX")).
                and(customer.custAddress.eq("北京"));
        Iterable<Customer> all = repository.findAll(and);
        for (Customer customer1 : all) {
            System.out.println(customer1);
        }
    }

    //模拟动态查询
    @Test
    public void test03() {
        Customer customer = new Customer();
        customer.setCustId(0l);
        customer.setCustName("XX,ZZ");
        customer.setCustAddress("北京");

        QCustomer QClass = QCustomer.customer;
        // 初始条件 类似于1=1 永远都成立的条件
        BooleanExpression expression = (QClass.isNotNull()).or(QClass.isNull());
        expression = customer.getCustId() > -1 ? expression.and(QClass.custId.gt(customer.getCustId())) : expression;
        expression = !StringUtils.isEmpty(customer.getCustName()) ? expression.and(QClass.custName.in(customer.getCustName().split(","))) : expression;
        expression = !StringUtils.isEmpty(customer.getCustAddress()) ? expression.and(QClass.custAddress.eq(customer.getCustAddress())) : expression;

        Iterable<Customer> all = repository.findAll(expression);
        for (Customer customer1 : all) {
            System.out.println(customer1);
        }
    }

    /**
     * 解决线程安全问题
     * 如果使用@autowire来对EntityManager进行注入（因为单例的方式注入bean），可能会出现线程安全问题
     * 使用@PersistenceContext时，为每一个线程单独绑定一个EntityManager，以解决线程不安全的问题
     */

    @PersistenceContext
    EntityManager em;

    /**
     * 自定义列查询、分组
     * 通过Repository进行查询， 列、表都是固定
     */
    @Test
    public void test04() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QCustomer qCustomer = QCustomer.customer;
        /**
         * Tuple是一个自定义对象，主要是负责来接收你要查询信息，
         * 比如你只需要查一张表中的两个字段，实际上是没有对象来接收这个查询结果的两个字段的，
         * 而提供的Tuple这个对象就可以用来接收这种类型的数据
         */
        JPAQuery<Tuple> tupleJPAQuery = jpaQueryFactory.select(qCustomer.custId, qCustomer.custName, qCustomer.custAddress).
                from(qCustomer).where(qCustomer.custId.gt(0l)).orderBy(qCustomer.custId.desc());

        // 执行查询
        List<Tuple> fetch = tupleJPAQuery.fetch();

        for (Tuple tuple : fetch) {
            System.out.println(tuple);
        }
    }
}
