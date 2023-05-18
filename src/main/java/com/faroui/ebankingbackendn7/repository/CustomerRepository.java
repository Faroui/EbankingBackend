package com.faroui.ebankingbackendn7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.faroui.ebankingbackendn7.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("select c from Customer c where c.name like :kw")
	List<Customer> findByNameContains(@Param("kw") String keywords);
}
