package com.fitbook.service;

import com.fitbook.dto.PaymentDto;
import com.fitbook.entity.Payment;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import com.fitbook.exception.DuplicateErrorException;
import com.fitbook.exception.RequestProcessingException;
import com.fitbook.repository.PaymentRepository;
import com.fitbook.util.DateUtil;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final UserService userService;

    private final ClientService clientService;

    private final TrainerService trainerService;

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(UserService userService, ClientService clientService, TrainerService trainerService,
                          PaymentRepository paymentRepository) {
        this.userService = userService;
        this.clientService = clientService;
        this.trainerService = trainerService;
        this.paymentRepository = paymentRepository;
        Stripe.apiKey = System.getenv("stripe_secretkey");
    }

    public String createCustomer(Trainer trainer) {
        return createCustomer(trainer.getFirstName() + " " + trainer.getLastName(), trainer.getUser().getEmail());
    }

    public String createCustomer(Client client) {
        return createCustomer(client.getFirstName() + " " + client.getLastName(), client.getUser().getEmail());
    }

    private String createCustomer(String name, String email) {
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("description", name);
        customerParams.put("email", email);

        try {
            Customer stripeCustomer = Customer.create(customerParams);
            System.out.println(stripeCustomer.getId());
            return stripeCustomer.getId();
        } catch (Exception e) {
            throw new RequestProcessingException("Payment failed.");
        }
    }

    public String chargeCreditCard(PaymentDto paymentDto, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = userService.findByEmail(email);
        Client client = clientService.findClientByUser(user);

        Trainer trainer = trainerService.findById(paymentDto.getTrainerId());

        int chargeAmountCents = (int) (paymentDto.getChargeAmount().doubleValue() * 100);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeAmountCents);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Monthly Charges");
        chargeParams.put("customer", client.getStripeId());

        try {
            Charge charge = Charge.create(chargeParams);
            System.out.println(charge);
            Payment payment = createPayment(paymentDto.getChargeAmount(), client, trainer, charge.getId());
            save(payment);
            return charge.getId();
        } catch (CardException e) {
            throw new RequestProcessingException(e.getMessage());
        } catch (Exception e) {
            throw new RequestProcessingException("Payment failed.");
        }
    }

    private Payment save(Payment payment) {
        try {
            return paymentRepository.save(payment);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateErrorException("Payment is already processed.");
        }
    }

    private Payment createPayment(Double amount, Client client, Trainer trainer, String chargeId) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setClient(client);
        payment.setTrainer(trainer);
        payment.setChargeId(chargeId);
        payment.setDate(DateUtil.now());
        return payment;
    }
}

