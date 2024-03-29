package de.mobile.service.impl;

import de.mobile.controller.dto.customer.CustomerDto;
import de.mobile.domain.customer.Customer;
import de.mobile.domain.customer.CustomerRole;
import de.mobile.mapper.CustomerMapper;
import de.mobile.repository.CustomerRepository;
import de.mobile.service.CustomerService;
import de.mobile.service.impl.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(final CustomerDto customerDto) {
        log.debug("Register customerDto = [" + customerDto + "]");
        Customer customer = inbound(customerDto);
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customer.setCustomerRole(CustomerRole.USER);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(final String id) {
        log.debug("Attempt to delete customer with id = [" + id + "]");
        Customer customer = customerRepository.findById(id);
        if (customer == null || !SecurityUtils.getUserId().equals(customer.getEmail())) {
            log.info("Customer with id = [" + id + "] not found");
            return;
        }
        customerRepository.delete(id);
    }

    private Customer inbound(CustomerDto customerDto) {
        return customerMapper.map(customerDto);
    }
}
