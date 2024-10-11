package com.assignment.accountmanagement.service;

import com.assignment.accountmanagement.dto.CreateCustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationReadyEventListener {
    private final CustomerService customerService;

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady() {
        CreateCustomerDTO customer1 = new CreateCustomerDTO(
                "Ipek",
                "Caglayan"
        );
        CreateCustomerDTO customer2 = new CreateCustomerDTO(
                "Second",
                "Customer"
        );
        customerService.createCustomer(customer1);
        customerService.createCustomer(customer2);

        log.info("Two customer created for testing purposes");
    }
}
