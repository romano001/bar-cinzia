package com.example.barcinzia.Controller;

import com.example.barcinzia.Entity.Item;
import com.example.barcinzia.Enum.ItemType;
import com.example.barcinzia.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/all")
    List<Item> getItems() {
        return itemService.fetchItemList();
    }

    @GetMapping("/all/{type}")
    List<Item> getItemsByType(@PathVariable("type") String type) {
        ItemType typeItem = ItemType.valueOf(type.toUpperCase());
        return itemService.fetchItemListByType(typeItem);
    }

    @GetMapping("/{name}")
    List<Item> getItemsByName(@PathVariable("name") String name) {
        return itemService.fetchItemListByName(name);
    }

    @GetMapping("/onsale")
    List<Item> getItemsOnSale() {
        return itemService.fetchItemOnSaleList();
    }

    @PostMapping("")
    Item createItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@RequestBody Item item, @PathVariable("id") Integer itemId) {
        return itemService.updateItem(item, itemId);
    }

    @DeleteMapping("/{id}")
    public String deleteItemById(@PathVariable("id") Integer itemId) {
        itemService.deleteItemById(itemId);
        return "Deleted Successfully";
    }

}
