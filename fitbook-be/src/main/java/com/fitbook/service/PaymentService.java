package com.fitbook.service;

import com.fitbook.entity.client.Client;
import com.fitbook.entity.trainer.Trainer;
import com.fitbook.exception.RequestProcessingException;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Customer;
import com.stripe.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final ClientService clientService;

    @Autowired
    public PaymentService(ClientService clientService) {
        this.clientService = clientService;
        Stripe.apiKey = System.getenv("stripe_secretkey");
    }

    public String createCustomer(Client client) {
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("description", client.getFirstName() + " " + client.getLastName());
        customerParams.put("email", client.getUser().getEmail());

        try {
            Customer stripeCustomer = Customer.create(customerParams);
            return stripeCustomer.getId();
        } catch (Exception e) {
            throw new RequestProcessingException("Payment failed.");
        }
    }

    public ProductData createProduct(Trainer trainer) {
        Map<String, Object> product = new HashMap<>();
        product.put("name", "Services");
        product.put("description", trainer.getFirstName() + " " + trainer.getLastName());
        Map<String, String> metadata = new HashMap<>();
        metadata.put("trainer_id", trainer.getId().toString());
        product.put("metadata", metadata);

        Map<String, Object> defaultPriceData = new HashMap<>();
        defaultPriceData.put("currency", "EUR");
        defaultPriceData.put("unit_amount", "1000");

        Map<String, String> recurringMap = new HashMap<>();
        recurringMap.put("interval", "month");
        recurringMap.put("interval_count", "1");

        defaultPriceData.put("recurring", recurringMap);

        product.put("default_price_data", defaultPriceData);

        try {
            Product stripeProduct = Product.create(product);
            return new ProductData(stripeProduct.getId(), stripeProduct.getDefaultPrice());
        } catch (StripeException e) {
            throw new RequestProcessingException("Failed to create product");
        }
    }

    static class ProductData {
        private final String productId;
        private final String priceId;

        public ProductData(String productId, String priceId) {
            this.productId = productId;
            this.priceId = priceId;
        }

        public String getProductId() {
            return productId;
        }

        public String getPriceId() {
            return priceId;
        }
    }

    public String getPriceIdByClientId(Long clientId) {
        Client client = clientService.findById(clientId);
        if (client.getTrainer() != null) {
            return client.getTrainer().getPriceId();
        }
        return "";
    }
}

