package com.example.barcinzia.Controller;

import com.example.barcinzia.Entity.OrderBar;
import com.example.barcinzia.Entity.OrderedItems;
import com.example.barcinzia.Model.ItemsInOrder;
import com.example.barcinzia.Model.SingleOrder;
import com.example.barcinzia.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    List<OrderBar> getOrders() throws net.minidev.json.parser.ParseException {
        log.info("Retrieve all orders");
        return orderService.fetchOrderList();
    }

    @GetMapping("/user")
    List<OrderBar> getOrdersByUserId() throws ParseException {
        log.info("Retrieve all orders for specific user");
        return orderService.fetchOrderListByUser();
    }

    @GetMapping("/items/{idOrder}")
    ResponseEntity getItemsByOrderId(@PathVariable("idOrder") Integer orderId) {
        log.info("Retrieve all items in single order");
        return orderService.fetchItemsListByOrder(orderId);
    }

    @PostMapping()
    ResponseEntity createOrder(@RequestBody SingleOrder orderBar) throws Exception {
        log.info("Create order");
        Date df = new SimpleDateFormat("dd/MM/yyyy").parse(orderBar.getDateOrder());
        if(df.before(Date.from(Instant.now().minus(1, ChronoUnit.DAYS)))){
            return new ResponseEntity("The order date cannot be earlier than today!", HttpStatus.NOT_ACCEPTABLE);
        }
        return orderService.saveOrder(orderBar);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@RequestBody SingleOrder orderBar, @PathVariable("id") Integer orderId) throws Exception {
        log.info("Update order");
        Date df = new SimpleDateFormat("dd/MM/yyyy").parse(orderBar.getDateOrder());
        System.out.println("Data: " + Date.from(Instant.now().minus(1, ChronoUnit.DAYS)));
        if(df.before(Date.from(Instant.now().minus(1, ChronoUnit.DAYS)))){
            return new ResponseEntity("The order date cannot be earlier than today!", HttpStatus.NOT_ACCEPTABLE);
        }
        return orderService.updateOrder(orderBar, orderId);
    }

    @DeleteMapping("/{id}")
    public String deleteOrderById(@PathVariable("id") Integer orderId) {
        log.info("Delete orders");
        orderService.deleteOrderById(orderId);
        return "Deleted Successfully";
    }
}
