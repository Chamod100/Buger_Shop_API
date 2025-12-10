package org.example.Service;

import edu.icet.model.entity.Customer;
import edu.icet.model.entity.Item;
import edu.icet.model.entity.Order;
import edu.icet.model.entity.OrderDetails;
import edu.icet.repository.CustomerRepository;
import edu.icet.repository.ItemRepository;
import edu.icet.repository.OrderDetailsRepository;
import edu.icet.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.example.Model.DTO.OrderDTO;
import org.example.Model.DTO.OrderDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Transactional
    public void addOrder(OrderDTO orderDto) {
        Customer customer = customerRepository.findByNic(orderDto.getCustomerNic());
        String orderId = generateOrderId();
        if (customer == null){
            throw new RuntimeException("Customer not found!");
        }
        LocalDate orderDate = LocalDate.parse(orderDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Order order = new Order(orderId, orderDate, customer);
        orderRepository.save(order);

        for (OrderDetailsDTO orderItem : orderDto.getOrderDetailsDtoArrayList()){
            Item item = itemRepository.findByName(orderItem.getItemName());
            if (item == null){
                throw new RuntimeException("Item not found!");
            }


            item.setQty(item.getQty() - orderItem.getQty());
            itemRepository.save(item);

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setItem(item);
            orderDetails.setQty(orderItem.getQty());
            orderDetailsRepository.save(orderDetails);
        }
    }

    //-------------------------------------Generate Order Id----------------------------------------------->
    public String generateOrderId() {
        List<Order> orderList = orderRepository.findAll();
        if (orderList.isEmpty()) {
            return "K001";
        }
        String id = orderList.get(orderList.size() - 1).getOrderId();
        int lastNo = Integer.parseInt(id.substring(1)) + 1;
        return String.format("K%03d", lastNo);
    }
}
