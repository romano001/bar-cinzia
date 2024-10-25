package com.example.barcinzia.Controller;

import com.example.barcinzia.Entity.Item;
import com.example.barcinzia.Enum.ItemType;
import com.example.barcinzia.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    List<Item> getItems() {
        return itemService.fetchItemList();
    }

    @GetMapping("/items/{type}")
    List<Item> getItemsByType(@PathVariable("type") ItemType type) {
        return itemService.fetchItemListByType(type);
    }

    @GetMapping("/items/{name}")
    List<Item> getItemsByName(@PathVariable("name") String name) {
        return itemService.fetchItemListByName(name);
    }

    @GetMapping("/items/onsale")
    List<Item> getItemsOnSale() {
        return itemService.fetchItemOnSaleList();
    }

    @PostMapping("/item")
    Item createItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    @PutMapping("/item/{id}")
    public Item updateItem(@RequestBody Item item, @PathVariable("id") Integer itemId) {
        return itemService.updateItem(item, itemId);
    }

    @DeleteMapping("/item/{id}")
    public String deleteItemById(@PathVariable("id") Integer itemId) {
        itemService.deleteItemById(itemId);
        return "Deleted Successfully";
    }

}
