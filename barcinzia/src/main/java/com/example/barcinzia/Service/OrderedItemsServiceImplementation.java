package com.example.barcinzia.Service;

import com.example.barcinzia.Entity.Item;
import com.example.barcinzia.Entity.OrderedItems;
import com.example.barcinzia.Repository.ItemRepository;
import com.example.barcinzia.Repository.OrderedItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderedItemsServiceImplementation implements OrderedItemsService {

    @Autowired
    private OrderedItemsRepository orderedItemsRepository;

    @Autowired
    private ItemRepository itemRepository;

    // Save operation
    @Override
    public OrderedItems saveOrderedItem(OrderedItems orderedItem)
    {
        Optional<Item> itemFromRepo = itemRepository.findById(orderedItem.getItem().getItemId());
        if(itemFromRepo.isPresent()){
            Item item = itemRepository.findById(orderedItem.getItem().getItemId()).get();
            orderedItem.setItem(item);
            return orderedItemsRepository.save(orderedItem);
        }
        return null;
    }

    // Read operation
    @Override
    public List<OrderedItems> fetchOrderedItemsList()
    {
        return (List<OrderedItems>) orderedItemsRepository.findAll();
    }

    // Update operation
    @Override
    public OrderedItems updateOrderedItem(OrderedItems orderedItem, Integer orderedItemId)
    {

        OrderedItems orderedItemDB = orderedItemsRepository.findById(orderedItemId).get();

        if (Objects.nonNull(orderedItem.getQuantity())) {
            orderedItem.setQuantity(orderedItem.getQuantity());
        }

        return orderedItemsRepository.save(orderedItemDB);
    }

    // Delete operation
    @Override
    public void deleteOrderedItemById(Integer orderedItemId)
    {
        orderedItemsRepository.deleteById(orderedItemId);
    }

}
