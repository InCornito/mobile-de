package de.mobile.mapper;

import de.mobile.controller.dto.customer.CustomerDto;
import de.mobile.domain.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "customerDtoType", source = "customer.customerType"),
            @Mapping(ignore = true, target = "password")
    })
    CustomerDto map(Customer customer);

    @Mappings({
            @Mapping(target = "customerType", source = "customerDto.customerDtoType")
    })
    Customer map(CustomerDto customerDto);
}
