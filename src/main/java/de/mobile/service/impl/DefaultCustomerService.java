package de.mobile.service.impl;

import de.mobile.controller.dto.customer.CustomerDto;
import de.mobile.domain.customer.Customer;
import de.mobile.mapper.CustomerMapper;
import de.mobile.repository.CustomerRepository;
import de.mobile.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(final CustomerDto customerDto) {
        Customer customer = inbound(customerDto);
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    private CustomerDto outbound(Customer customer) {
        return customerMapper.map(customer);
    }

    private Customer inbound(CustomerDto customerDto) {
        return customerMapper.map(customerDto);
    }
}
