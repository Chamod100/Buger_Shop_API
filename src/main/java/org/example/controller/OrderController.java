package org.example.controller;

import edu.icet.service.OrderService;
import org.example.Model.DTO.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("add")
    public void addOrder(@RequestBody OrderDTO orderDTO){
        orderService.addOrder(orderDTO);
    }
}
