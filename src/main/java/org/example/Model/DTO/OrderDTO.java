package org.example.Model.DTO;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private String customerNic;
    private String date;
    private ArrayList<OrderDetailsDTO> orderDetailsDtoArrayList;
}
