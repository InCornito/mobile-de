package de.mobile.controller.dto.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.mobile.controller.validator.AlphaNumericName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class CustomerDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @AlphaNumericName
    private String firstName;
    @AlphaNumericName
    private String lastName;
    private String companyName;

    @NotNull
    @JsonProperty("customerType")
    private CustomerDtoType customerDtoType;

    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
