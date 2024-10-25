package com.example.barcinzia.Repository;

import com.example.barcinzia.Entity.OrderBar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderBar, Integer> {
}
