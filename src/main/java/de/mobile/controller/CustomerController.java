package de.mobile.controller;

import de.mobile.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping(value = "customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @DeleteMapping(value = "/{id}")
    public void register(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
    }
}
