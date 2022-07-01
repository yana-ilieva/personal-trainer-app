package com.fitbook.service;

import com.fitbook.dto.RegistrationDto;
import com.fitbook.dto.UserDto;
import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;

    private final TrainerService trainerService;

    private final ClientService clientService;

    private final PaymentService paymentService;

    @Autowired
    public RegistrationService(UserService userService, TrainerService trainerService, ClientService clientService, PaymentService paymentService) {
        this.userService = userService;
        this.trainerService = trainerService;
        this.clientService = clientService;
        this.paymentService = paymentService;
    }

    public void register(RegistrationDto registrationDto) {
        UserDto userDto = UserDto.builder()
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .role(registrationDto.getRole())
                .build();

        User user = userService.save(userDto);
        createEntity(user, registrationDto);
    }

    private void createEntity(User user, RegistrationDto registrationDto) {
        if (user.getRole().getName().equals("ROLE_TRAINER")) {
            Trainer trainer = trainerService.create(user, registrationDto);
            PaymentService.ProductData productData = paymentService.createProduct(trainer);
            trainer.setProductId(productData.getProductId());
            trainer.setPriceId(productData.getPriceId());
            trainerService.save(trainer);
        }
        if (user.getRole().getName().equals("ROLE_CLIENT")) {
            Client client = clientService.create(user, registrationDto);
            String stripeId = paymentService.createCustomer(client);
            client.setStripeId(stripeId);
            clientService.save(client);
        }
    }
}
