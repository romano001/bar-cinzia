package com.example.barcinzia.Repository;

import com.example.barcinzia.Entity.OrderedItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemsRepository extends CrudRepository<OrderedItems, Integer> {
}
