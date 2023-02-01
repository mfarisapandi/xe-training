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

    RestTemplate restTemplate = new RestTemplate();

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String GET_ALL_WIZARD_API = "http://localhost:8090/api/xe/wizardList";
    public String GET_ALL_WAND_API = "http://localhost:8070/api/xe/magicWandList";
    public String GET_WAND_UPDATE_API = "http://localhost:8070/api/xe/update";
    @Autowired
    OrderRepository orderRepository;

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

        ResponseEntity<List<WizardPojo>> responseWizard = restTemplate.exchange(GET_ALL_WIZARD_API, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });

        ResponseEntity<List<MagicWandPojo>> responseMagicWand = restTemplate.exchange(GET_ALL_WAND_API, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {
                });

        LOGGER.info("Adding order to repository.");

        for (WizardPojo wizardPojo : responseWizard.getBody()) {
            LOGGER.info(String.valueOf(order.getWizardID()));
            LOGGER.info(String.valueOf(wizardPojo.getWizardID()));
            if (wizardPojo.getWizardID() == order.getWizardID()) {
                if (wizardPojo.getIsActive().equals("Y")) {
                    order.setWizardID(wizardPojo.getWizardID());
                    order.setWizardName(wizardPojo.getWizardName());
                    order.setWizardAge(wizardPojo.getWizardAge());
                    order.setJoinedDate(wizardPojo.getJoinedDate());

                    for (MagicWandPojo magicWandPojo : responseMagicWand.getBody()) {
                        if (magicWandPojo.getMagicWandID() == order.getMagicWandID()) {
                            if (order.getWizardAge() < magicWandPojo.getAgeLimit()) {
                                if (magicWandPojo.getWandStock() > 0) {
                                    order.setMagicWandID(magicWandPojo.getMagicWandID());
                                    order.setMagicWandName(magicWandPojo.getMagicWandName());
                                    order.setMagicWandDesc(magicWandPojo.getMagicWandDesc());

                                    magicWandPojo.setWandStock(magicWandPojo.getWandStock() - 1);

                                    orderRepository.save(order);

                                } else {
                                    return "Magic Wand ID: " + magicWandPojo.getMagicWandID() + "\nName: " + magicWandPojo.getMagicWandName() +
                                            "\nis out of stock";
                                }
                            } else {
                                return order.getWizardName() + " age is: " + order.getWizardAge() + ". The age is exceeds the age limit." +
                                        "\nAge limit: " + magicWandPojo.getAgeLimit();
                            }
                        } else {
                            LOGGER.info(String.valueOf(order.getWizardID()));
                            LOGGER.info(String.valueOf(wizardPojo.getWizardID()));
                            LOGGER.info(String.valueOf(order.getMagicWandID()));
                            LOGGER.info(String.valueOf(magicWandPojo.getMagicWandID()));
                            return "Invalid Magic Wand ID";
                        }
                    }
                } else {
                    return "Wizard with ID: " + wizardPojo.getWizardID() + " is not active";
                }
            } else {
                LOGGER.info(String.valueOf(order.getWizardID()));
                LOGGER.info(String.valueOf(wizardPojo.getWizardID()));
                LOGGER.info(String.valueOf(order.getMagicWandID()));
                return "Invalid Wizard ID";
            }
        }

        return "Order created";
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
