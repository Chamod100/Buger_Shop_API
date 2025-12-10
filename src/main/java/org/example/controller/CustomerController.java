package org.example.controller;

import edu.icet.service.CustomerService;
import org.example.Model.DTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("getAll")
    public ArrayList<CustomerDTO> getAll(){
        return customerService.getAll();
    }

    @PostMapping("add")
    public void addCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
    }

    @PostMapping("update")
    public void updateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerDTO);
    }

    @DeleteMapping("delete/{nic}")
    public void deleteCustomer(@PathVariable String nic){
        customerService.deleteCustomer(nic);
    }

}
