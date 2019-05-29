package de.mobile.domain.customer;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = Customer.Meta.DOCUMENT_NAME)
public class Customer {
    
    private String id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;

    public interface Meta {
        String DOCUMENT_NAME = "customers";
    }
}
