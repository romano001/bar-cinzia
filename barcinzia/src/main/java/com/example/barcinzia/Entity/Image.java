package com.example.barcinzia.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer imageId;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String description;

    @OneToOne(mappedBy = "image")
    @JsonIgnore
    private Item item;
}
