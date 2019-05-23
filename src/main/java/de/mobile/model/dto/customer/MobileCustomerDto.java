package de.mobile.model.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MobileCustomerDto {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
}
