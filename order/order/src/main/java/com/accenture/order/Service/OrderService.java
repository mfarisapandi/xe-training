package com.accenture.order.Service;

import com.accenture.order.Entity.*;
import com.accenture.order.Repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import java.util.*;
import java.util.logging.*;

@Service
public class OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    @Autowired
    RestTemplate restTemplate;
    public String GET_ALL_WIZARD_API = "http://localhost:8090/api/xe/wizardList";
    public String GET_ALL_WAND_API = "http://localhost:8070/api/xe/magicWandList";
    public String GET_WAND_UPDATE_API = "http://localhost:8070/api/xe/update";
    @Autowired
    private OrderRepository orderRepository;

    public String getList() {
        try {
            String responseFromWizard = restTemplate.getForObject(GET_ALL_WIZARD_API, String.class);
            String responseFromWand = restTemplate.getForObject(GET_ALL_WAND_API, String.class);

            LOGGER.info("Successfully retrieved wizard and wand lists.");
            return ("Wizards list = " + responseFromWizard + "\nMagic Wands List = " + responseFromWand);

        } catch (RestClientException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving wizard and wand lists.", e);
            return "Error";
        }
    }

    public List<Order> getOrder() {
        LOGGER.info("Retrieving all orders from repository.");
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            LOGGER.severe("No orders found");
        }
        return orders;
    }

    public String addOrder(Order order) {
        try {

            ResponseEntity<List<WizardPojo>> responseWizard = restTemplate.exchange(GET_ALL_WIZARD_API, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<WizardPojo>>() {
                    });

            ResponseEntity<List<MagicWandPojo>> responseMagicWand = restTemplate.exchange(GET_ALL_WAND_API, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<MagicWandPojo>>() {
                    });

            LOGGER.info("Adding order to repository.");

            for (WizardPojo wizardPojo : responseWizard.getBody()) {
                if (wizardPojo.getWizard_id() == order.getWizardID()) {
                    if (wizardPojo.getIs_active().equals("N")) {
                        return wizardPojo.getWizard_name() + "status currently not active";
                    }
                    order.setWizardName(wizardPojo.getWizard_name());
                    order.setAgeLimit(wizardPojo.getWizard_age());

                    for (MagicWandPojo magicWandPojo : responseMagicWand.getBody()) {
                        if (magicWandPojo.getMagic_wand_id() == order.getMagicWandID()) {
                            if (order.getWizardAge() > magicWandPojo.getAge_limit()) {
                                return wizardPojo.getWizard_name() + "age is: " + order.getWizardAge() + ". The age is exceeds " +
                                        magicWandPojo.getMagic_wand_name() + " limit age which is " + magicWandPojo.getAge_limit();
                            }
                            if (magicWandPojo.getStock() <= 0) {
                                return magicWandPojo.getStock() + " is out of stock";
                            }
                            order.setMagicWandName(magicWandPojo.getMagic_wand_name());
                            order.setMagicWandDesc((magicWandPojo.getMagic_wand_desc()));

                            order.setStock(magicWandPojo.getStock() - 1);
                            order.setAgeLimit(magicWandPojo.getAge_limit());

                            magicWandPojo.setStock(order.getStock());

                            HttpEntity<MagicWandPojo> request = new HttpEntity<>(magicWandPojo);
                            ResponseEntity<String> responseUpdate = restTemplate.exchange(GET_WAND_UPDATE_API + "/" +
                                    order.getMagicWandID(), HttpMethod.PUT, request, String.class);

                            orderRepository.save(order);
                            return "Order created successfully";
                        }
                    }

                    return "Invalid Magic Wand ID";
                }
            }

            return "Invalid Wizard ID ";

        } catch (RestClientException e) {
            LOGGER.log(Level.SEVERE, "Error adding order.", e);
            return "Error";
        }
    }
    public Order updateOrder(Order order) {
        LOGGER.info("Updating order in repository.");
        return orderRepository.save(order);
    }

    public void deleteOrder(int orderID) {
        LOGGER.info("Deleting order with ID: " + orderID + " from repository.");
        orderRepository.deleteById(orderID);
    }
}
