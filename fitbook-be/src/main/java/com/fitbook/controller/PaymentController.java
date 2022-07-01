package com.fitbook.controller;

import com.fitbook.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/client/{clientId}")
    public String getPriceIdByClientId(@PathVariable("clientId") Long clientId) {
        return paymentService.getPriceIdByClientId(clientId);
    }
}
