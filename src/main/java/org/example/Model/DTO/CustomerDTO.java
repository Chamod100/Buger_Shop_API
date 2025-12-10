package org.example.Model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {

    private String title;
    private String name;
    private String dob;
    private String  nic;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}
