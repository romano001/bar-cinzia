package com.example.barcinzia.Controller;

import com.example.barcinzia.Entity.OrderBar;
import com.example.barcinzia.Model.SingleOrder;
import com.example.barcinzia.Service.OrderService;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    List<OrderBar> getOrders() throws net.minidev.json.parser.ParseException {
        return orderService.fetchOrderList();
    }

    @GetMapping("/user")
    List<OrderBar> getOrdersByUserId() throws ParseException {
        return orderService.fetchOrderListByUser();
    }

    @PostMapping()
    ResponseEntity createOrder(@RequestBody SingleOrder orderBar) throws Exception {
        Date df = new SimpleDateFormat("dd/MM/yyyy").parse(orderBar.getDateOrder());
        if(df.before(new Date())){
            return new ResponseEntity("The order date cannot be earlier than today!", HttpStatus.NOT_ACCEPTABLE);
        }
        return orderService.saveOrder(orderBar);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@RequestBody SingleOrder orderBar, @PathVariable("id") Integer orderId) throws Exception {
        Date df = new SimpleDateFormat("dd/MM/yyyy").parse(orderBar.getDateOrder());
        if(df.before(new Date())){
            return new ResponseEntity("The order date cannot be earlier than today!", HttpStatus.NOT_ACCEPTABLE);
        }
        return orderService.updateOrder(orderBar, orderId);
    }

    @DeleteMapping("/{id}")
    public String deleteOrderById(@PathVariable("id") Integer orderId) {
        orderService.deleteOrderById(orderId);
        return "Deleted Successfully";
    }
}
