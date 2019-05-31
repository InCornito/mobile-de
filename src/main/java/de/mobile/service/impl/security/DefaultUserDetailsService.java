package de.mobile.service.impl.security;

import de.mobile.controller.dto.customer.CustomerDto;
import de.mobile.domain.customer.Customer;
import de.mobile.mapper.CustomerMapper;
import de.mobile.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class DefaultUserDetailsService implements de.mobile.service.UserDetailsService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = getCustomer(email);

        return new User(
                customer.getEmail(),
                customer.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }

    @Override
    public CustomerDto getUserByEmail(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.map(customer);
    }

    private Customer getCustomer(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException(email);
        }
        return customer;
    }

}
