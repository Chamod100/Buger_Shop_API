package org.example.Service;

import org.example.Model.DTO.ItemDTO;
import org.example.Model.Entity.Item;
import org.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public ArrayList<ItemDTO> getAllItem() {
        List<Item> itemList = itemRepository.findAll();
        ArrayList<ItemDTO> itemDtos = new ArrayList<>();

        for (Item item : itemList) {
            itemDtos.add(
                    new ItemDTO(
                            item.getName(),
                            item.getQty(),
                            item.getPrice(),
                            item.isAvailable(),
                            item.getImage()
                    )
            );
        }
        return itemDtos;
    }

    public void addItem(ItemDTO itemDTO) {
        itemRepository.save(
                new Item(
                        generateCustomerId(),
                        itemDTO.getName(),
                        itemDTO.getQty(),
                        itemDTO.getPrice(),
                        itemDTO.isAvailable(),
                        itemDTO.getImage()
                )
        );
    }

    public String generateCustomerId() {
        List<Item> itemList = itemRepository.findAll();
        if (itemList.isEmpty()) {
            return "I001";
        }
        String id = itemList.get(itemList.size() - 1).getId();
        int lastNo = Integer.parseInt(id.substring(1)) + 1;
        return String.format("I%03d", lastNo);
    }

    public void upadateItem(ItemDTO itemDTO) {
        Item itemByName = itemRepository.findByName(itemDTO.getName());
        if (itemByName != null){
            itemByName.setName(itemDTO.getName());
            itemByName.setQty(itemDTO.getQty());
            itemByName.setPrice(itemDTO.getPrice());
            itemByName.setAvailable(itemDTO.isAvailable());
            itemByName.setImage(itemDTO.getImage());
            itemRepository.save(itemByName);
        }
    }

    public void deleteItem(String name) {
        itemRepository.delete(itemRepository.findByName(name));
    }
}