package org.example.controller;

import org.example.Model.DTO.ItemDTO;
import org.example.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("all")
    public ArrayList<ItemDTO> getAllItem(){
        return itemService.getAllItem();
    }

    @PostMapping("add")
    public void addItem(@RequestBody ItemDTO itemDTO){
        itemService.addItem(itemDTO);
    }

    @PostMapping("update")
    public void updateItem(@RequestBody ItemDTO itemDTO){
        itemService.upadateItem(itemDTO);
    }

    @DeleteMapping("delete/{name}")
    public void deleteItem(@PathVariable String name){
        itemService.deleteItem(name);
    }
}
