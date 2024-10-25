package com.example.barcinzia.Entity;

import com.example.barcinzia.Enum.ItemType;
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
@Entity(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;

    private String name;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    private Double price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "imageId")
    private Image image;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<OrderedItems> orderedItems;

}
