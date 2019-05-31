package de.mobile.service;

import de.mobile.controller.dto.customer.CustomerDto;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
    CustomerDto getUserByEmail(String email);
}
