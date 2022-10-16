package com.tuling;

/*
@author YG
@create 2022/10/16   13:26
*/

import com.tuling.config.SpringDataJPAConfig;
import com.tuling.pojo.Customer;
import com.tuling.repositories.CustomerSpecificationsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpecificationTest {
    @Autowired
    CustomerSpecificationsRepository repository;

    @Test
    public void test01() {
        Customer customer = new Customer();
        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> custId = root.get("custId");
                Path<Object> custName = root.get("custName");
                Path<Object> custAddress = root.get("custAddress");

                Predicate predicate = criteriaBuilder.equal(custAddress, "China");
                return predicate;
            }
        });
        for (Customer customer1 : customers) {
            System.out.println(customer1);
        }
    }

    //进行多条件查询
    @Test
    public void test02() {
        Customer customer = new Customer();
        List<Customer> all = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Long> custId = root.get("custId");
                Path<Object> custName = root.get("custName");
                Path<Object> custAddress = root.get("custAddress");

                Predicate addr = criteriaBuilder.equal(custAddress, "China");
                Predicate greaterThan = criteriaBuilder.greaterThan(custId, 7L);
                CriteriaBuilder.In<Object> in = criteriaBuilder.in(custName);
                in.value("YYY").value("YY").value("XX");

                // 组合条件
                Predicate and = criteriaBuilder.and(addr, greaterThan, in);

                return and;
            }
        });

        for (Customer customer1 : all) {
            System.out.println(customer1);
        }
    }

    //进行排序等多条件的操作
    @Test
    public void test03() {
        Customer customer = new Customer();
//        customer.setCustAddress("China");
        customer.setCustName("YY");
        customer.setCustId(0L);

        List<Customer> all = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 1. 通过root拿到需要设置条件的字段
                Path<Long> custId = root.get("custId");
                Path<String> custName = root.get("custName");
                Path<String> custAddress = root.get("custAddress");

                // 2. 通过CriteriaBuilder设置不同类型条件
                List<Predicate> list = new ArrayList();
                if (!StringUtils.isEmpty(customer.getCustAddress())){
                    list.add(criteriaBuilder.equal(custAddress,"北京"));
                }
                if (!StringUtils.isEmpty(customer.getCustName())){
                    CriteriaBuilder.In<String> in = criteriaBuilder.in(custName);
                    in.value("XX").value("China").value("易一");
                    list.add(in);
                }
                if (customer.getCustId()>-1L){
                    list.add(criteriaBuilder.greaterThan(custId,0L));
                }
                Predicate[] predicates = list.toArray(new Predicate[list.size()]);
                Order desc = criteriaBuilder.desc(custId);
                //使用query排序
                Predicate restriction = query.where(predicates).orderBy(desc).getRestriction();
                return restriction;
            }
        });

        for (Customer customer1 : all) {
            System.out.println(customer1);
        }
    }
}
