package com.example.barcinzia.Service;

import com.example.barcinzia.Entity.Item;
import com.example.barcinzia.Enum.ItemType;
import com.example.barcinzia.Repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ItemServiceImplementation implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    // Save operation
    @Override
    public Item saveItem(Item item)
    {
        return itemRepository.save(item);
    }

    // Read operation
    @Override public List<Item> fetchItemList()
    {
        return (List<Item>) itemRepository.findAll();
    }

    @Override public List<Item> fetchItemListByType(ItemType type)
    {
        List<Item> itemsFiltered = new ArrayList<>(0);
        List<Item> items = (List<Item>) itemRepository.findAll();

        for (Item item : items) {
            if (item.getType() == type) {
                itemsFiltered.addLast(item);
            }
        }

        return itemsFiltered;
    }

    @Override public List<Item> fetchItemListByName(String name)
    {
        List<Item> itemsFiltered = new ArrayList<>(0);
        List<Item> items = (List<Item>) itemRepository.findAll();

        for (Item item : items) {
            if (item.getName().contains(name)) {
                itemsFiltered.addLast(item);
            }
        }

        return itemsFiltered;
    }

    @Override public List<Item> fetchItemOnSaleList()
    {
        List<Item> itemsOnSale = new ArrayList<>(0);
        List<Item> items = (List<Item>) itemRepository.findAll();

        Double sale = 0.2;

        if(items.isEmpty()){
            return itemsOnSale;
        }

        if(items.size() == 1){
            Item firstElement = items.getFirst();
            firstElement.setPrice((double) Math.round((firstElement.getPrice() - (firstElement.getPrice() * sale)) * 100) / 100);
            itemRepository.save(firstElement);
            itemsOnSale.addLast(firstElement);
        }
        else {
            Collections.shuffle(items);
            Item firstElement = items.getFirst();
            firstElement.setPrice((double) Math.round(firstElement.getPrice() - (firstElement.getPrice() * sale)));
            Item secondElement = items.get(1);
            secondElement.setPrice((double) Math.round(secondElement.getPrice() - (secondElement.getPrice() * sale)));
            itemRepository.save(firstElement);
            itemRepository.save(secondElement);
            itemsOnSale.addLast(firstElement);
            itemsOnSale.addLast(secondElement);
        }

        return itemsOnSale;
    }

    // Update operation
    @Override
    public Item updateItem(Item item, Integer itemId)
    {

        Item itemDB = itemRepository.findById(itemId).get();

        if (Objects.nonNull(item.getName()) && !"".equalsIgnoreCase(item.getName())) {
            itemDB.setName(item.getName());
        }

        if (Objects.nonNull(item.getType())) {
            itemDB.setType(item.getType());
        }

        if (Objects.nonNull(item.getPrice())) {
            itemDB.setPrice(item.getPrice());
        }

        if (Objects.nonNull(item.getImage())) {
            itemDB.setImage(item.getImage());
        }

        if (Objects.nonNull(item.getOrderedItems())) {
            itemDB.setOrderedItems(item.getOrderedItems());
        }

        return itemRepository.save(itemDB);
    }

    // Delete operation
    @Override
    public void deleteItemById(Integer itemId)
    {
        itemRepository.deleteById(itemId);
    }

}
