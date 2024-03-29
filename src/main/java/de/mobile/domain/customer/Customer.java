package de.mobile.domain.customer;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@ToString(exclude = "password")
@Document(collection = Customer.Meta.DOCUMENT_NAME)
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String companyName;

    private CustomerRole customerRole;
    private CustomerType customerType;

    @Indexed(unique = true)
    private String email;
    private String password;

    public interface Meta {
        String DOCUMENT_NAME = "customers";
    }
}
