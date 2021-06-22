package com.example.store.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product { //extends Model
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
    private String size;
    private String description;
    private Integer price;
    private Integer pieces_in_stock; // количество в наличии

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="model_id")
    private Model model;

}
