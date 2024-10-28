package com.example.barcinzia.Model;

import com.example.barcinzia.Entity.OrderedItems;
import lombok.Data;

import java.util.List;

@Data
public class SingleOrder {

    private String dateOrder;

    private List<OrderedItems> orderedItemsList;
}
