package de.mobile.controller.dto.customer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class MobileCustomerDto {
    
    private String id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
}
