package org.example.Service;

import edu.icet.model.entity.Customer;
import edu.icet.repository.CustomerRepository;
import org.example.Model.DTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    //-----------------------------Get All Customer----------------------------------->
    public ArrayList<CustomerDTO> getAll() {
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        for (Customer customer : customerList) {
            allCustomer.add(
                    new CustomerDTO(
                            customer.getTitle(),
                            customer.getName(),
                            customer.getDob(),
                            customer.getNic(),
                            customer.getAddress(),
                            customer.getCity(),
                            customer.getProvince(),
                            customer.getPostalCode()
                    )
            );
        }
        return allCustomer;
    }

    //------------------------------------------------Add Customer----------------------------------------->
    public void addCustomer(CustomerDTO customerDTO) {
        customerRepository.save(new Customer(
                generateCustomerId(),
                customerDTO.getTitle(),
                customerDTO.getName(),
                customerDTO.getDob(),
                customerDTO.getNic(),
                customerDTO.getAddress(),
                customerDTO.getCity(),
                customerDTO.getProvince(),
                customerDTO.getPostalCode()
        ));
    }

    //--------------------------------------------Generate CustomerId-------------------------------------->
    public String generateCustomerId() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()) {
            return "C001";
        }
        String id = customerList.get(customerList.size() - 1).getId();
        int lastNo = Integer.parseInt(id.substring(1)) + 1;
        return String.format("C%03d", lastNo);
    }

    //-----------------------------------------Update Customer----------------------------------------------->
    public void updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByNic(customerDTO.getNic());
        if (customer != null){
            customer.setName(customerDTO.getName());
            customer.setDob(customerDTO.getDob());
            customer.setTitle(customerDTO.getTitle());
            customer.setAddress(customerDTO.getAddress());
            customer.setCity(customerDTO.getCity());
            customer.setProvince(customerDTO.getProvince());
            customer.setPostalCode(customerDTO.getPostalCode());
            customerRepository.save(customer);
        }
    }

    //-----------------------------------------Delete Customer--------------------------------->
    public void deleteCustomer(String nic) {
        Customer customer = customerRepository.findByNic(nic);
        if (customer != null) {
            customerRepository.delete(customer);
        }
    }

}
