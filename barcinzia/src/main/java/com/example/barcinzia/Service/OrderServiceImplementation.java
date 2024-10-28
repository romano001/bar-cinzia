package com.example.barcinzia.Service;

import com.example.barcinzia.Entity.Item;
import com.example.barcinzia.Entity.OrderBar;
import com.example.barcinzia.Entity.OrderedItems;
import com.example.barcinzia.Model.SingleOrder;
import com.example.barcinzia.Repository.ItemRepository;
import com.example.barcinzia.Repository.OrderRepository;
import com.example.barcinzia.Repository.OrderedItemsRepository;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderedItemsRepository orderedItemsRepository;

    private String token() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtAuthenticationToken oauthToken = (JwtAuthenticationToken) authentication;
        return oauthToken.getToken().getTokenValue();
    }

    private JSONObject decodeToken(String token) throws ParseException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("\\.")[1]));
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(payload);
        return json;
    }

    // Save operation
    @Override
    public ResponseEntity saveOrder(SingleOrder orderBar) throws Exception
    {
        OrderBar orderFinal = new OrderBar();
        orderFinal.setIdUser((String) decodeToken(token()).get("name"));
        orderFinal.setDateOrder(orderBar.getDateOrder());
        List<OrderedItems> orderedItems = orderBar.getOrderedItemsList();
        List<OrderedItems> orderListItems = new ArrayList<>(0);

        //List of ordered items
        for(OrderedItems orderItem : orderedItems){
            Optional<Item> itemFromRepo = itemRepository.findById(orderItem.getItem().getItemId());
            if(itemFromRepo.isPresent()){
                Item item = itemRepository.findById(orderItem.getItem().getItemId()).get();
                OrderedItems order = new OrderedItems();
                order.setQuantity(orderItem.getQuantity());
                order.setItem(item);
                orderListItems.addLast(order);
                order.setOrderBar(orderFinal);
                orderedItemsRepository.save(order);
            }
        }
        orderFinal.setOrderedItems(orderListItems);
        return new ResponseEntity(orderRepository.save(orderFinal), HttpStatus.OK);
    }

    // Read operation
    @Override
    public List<OrderBar> fetchOrderList() {
        return (List<OrderBar>) orderRepository.findAll();
    }

    @Override
    public List<OrderBar> fetchOrderListByUser() throws ParseException {
        List<OrderBar> orderBarByUser = new ArrayList<>(0);
        List<OrderBar> ordersBar = (List<OrderBar>)  orderRepository.findAll();

        String user = (String) decodeToken(token()).get("name");

        for(OrderBar orderBar : ordersBar){
            if(Objects.equals(orderBar.getIdUser(), user)){
                orderBarByUser.addLast(orderBar);
            }
        }

        return orderBarByUser;
    }

    // Update operation
    @Override
    public ResponseEntity updateOrder(SingleOrder orderBar, Integer orderId)
    {
        OrderBar orderBarDB = orderRepository.findById(orderId).get();

        if (Objects.nonNull(orderBar.getDateOrder()) && !"".equalsIgnoreCase(orderBar.getDateOrder())) {
            orderBarDB.setDateOrder(orderBar.getDateOrder());
        }

        List<OrderedItems> orderedItems = orderBar.getOrderedItemsList();
        List<OrderedItems> orderListItems = new ArrayList<>(0);

        //List of ordered items
        for(OrderedItems orderItem : orderedItems){
            Optional<Item> itemFromRepo = itemRepository.findById(orderItem.getItem().getItemId());
            if(itemFromRepo.isPresent()){
                Item item = itemRepository.findById(orderItem.getItem().getItemId()).get();
                OrderedItems order = new OrderedItems();
                order.setOrderedItemId(orderItem.getOrderedItemId());
                order.setQuantity(orderItem.getQuantity());
                order.setItem(item);
                orderListItems.addLast(order);
                order.setOrderBar(orderBarDB);
                if(orderItem.getQuantity() > 0) {
                    orderedItemsRepository.save(order);
                }
                else {
                    orderedItemsRepository.delete(order);
                }
            }
        }

        return new ResponseEntity(orderRepository.save(orderBarDB), HttpStatus.OK);
    }

    // Delete operation
    @Override
    public void deleteOrderById(Integer orderId)
    {
        orderRepository.deleteById(orderId);
    }

}
