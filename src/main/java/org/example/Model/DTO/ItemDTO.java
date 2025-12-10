package org.example.Model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {

    private String name;
    private int qty;
    private double price;
    private boolean available;
    private String image;

}
