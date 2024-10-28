package com.example.barcinzia.Entity;

import com.example.barcinzia.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orderBar")
public class OrderBar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    private String idUser;

    @Column(nullable = false)
    private String dateOrder;

    @OneToMany(mappedBy = "orderBar", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderedItems> orderedItems;

    private OrderStatus statusOrder;

}
