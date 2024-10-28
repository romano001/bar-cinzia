package com.example.barcinzia.Model;
import com.example.barcinzia.Entity.Item;

import lombok.Data;

@Data
public class ItemsInOrder {

    private Integer idOrder;
    private Item item;
    private Integer quantity;
}
