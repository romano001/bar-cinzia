package com.example.barcinzia.Service;

import com.example.barcinzia.Entity.Item;
import com.example.barcinzia.Enum.ItemType;

import java.util.List;

public interface ItemService {

    // Save operation
    Item saveItem(Item item);

    // Read operation
    List<Item> fetchItemList();
    List<Item> fetchItemListByType(ItemType type);
    List<Item> fetchItemListByName(String name);
    List<Item> fetchItemOnSaleList();

    // Update operation
    Item updateItem(Item item, Integer itemId);

    // Delete operation
    void deleteItemById(Integer itemId);

}
