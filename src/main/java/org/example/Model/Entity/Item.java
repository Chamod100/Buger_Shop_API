package org.example.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Item {
    @Id
    private String id;
    @Column(unique = true)
    private String name;
    private int qty;
    private double price;
    private boolean available;
    private String image;
}
