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
public class Customer {
    @Id
    private String id;
    private String title;
    private String name;
    private String dob;
    @Column(unique = true)
    private String  nic;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}
