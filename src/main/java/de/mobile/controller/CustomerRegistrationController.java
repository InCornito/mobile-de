package de.mobile.controller;

import de.mobile.controller.dto.customer.CustomerDto;
import de.mobile.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RequiredArgsConstructor
@RestController
public class CustomerRegistrationController {

    private final CustomerService customerService;

    @PostMapping(value = "/public/register")
    public void register(@RequestBody @Valid @NotNull CustomerDto customerDto) {
        customerService.register(customerDto);
    }
}
