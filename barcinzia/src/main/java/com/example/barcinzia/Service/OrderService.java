package com.example.barcinzia.Service;

import com.example.barcinzia.Entity.OrderBar;
import com.example.barcinzia.Entity.OrderedItems;
import com.example.barcinzia.Model.SingleOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    // Save operation
    ResponseEntity saveOrder(SingleOrder orderBar) throws Exception;

    // Read operation
    List<OrderBar> fetchOrderList();
    List<OrderBar> fetchOrderListByUser(String idUser);

    // Update operation
    ResponseEntity updateOrder(SingleOrder orderBar, Integer orderId);

    // Delete operation
    void deleteOrderById(Integer orderId);
}