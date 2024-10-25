package com.example.barcinzia.Service;

import com.example.barcinzia.Entity.OrderedItems;

import java.util.List;

public interface OrderedItemsService {

    // Save operation
    OrderedItems saveOrderedItem(OrderedItems orderedItem);

    // Read operation
    List<OrderedItems> fetchOrderedItemsList();

    // Update operation
    OrderedItems updateOrderedItem(OrderedItems orderedItem, Integer orderedItemId);

    // Delete operation
    void deleteOrderedItemById(Integer orderedItemId);

}
