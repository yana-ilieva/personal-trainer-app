package com.fitbook.controller;

import com.fitbook.dto.PaymentDto;
import com.fitbook.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public String completePayment(@RequestBody PaymentDto paymentDto, Authentication authentication) {
        return paymentService.chargeCreditCard(paymentDto, authentication);
    }
}
