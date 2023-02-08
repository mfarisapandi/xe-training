package com.accenture.order.Service;

import com.accenture.order.Entity.*;
import com.accenture.order.Exception.*;
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

    private static final Logger log = Logger.getLogger(OrderService.class.getName());

    RestTemplate restTemplate;

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

            log.info("Successfully retrieved wizard and wand lists.");
            return ("Wizards list = " + responseFromWizard + "\nMagic Wands List = " + responseFromWand);

        } catch (RestClientException e) {
            log.log(Level.SEVERE, "Error retrieving wizard and wand lists.", e);
            return "Error";
        }
    }

    public List<Order> getOrder() {
        log.info("Retrieving all orders from repository.");
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            log.severe("No orders found");
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

        log.info("Adding order to repository.");

        for (WizardPojo wizardPojo : responseWizard.getBody()) {

            log.info(String.valueOf(order.getWizardID()));
            log.info(String.valueOf(wizardPojo.getWizardID()));

            if (wizardPojo.getWizardID() == order.getWizardID()) {
                if (wizardPojo.getIsActive().equals("Y")) {
                    order.setWizardID(wizardPojo.getWizardID());
                    order.setWizardName(wizardPojo.getWizardName());
                    order.setWizardAge(wizardPojo.getWizardAge());
                    order.setJoinedDate(wizardPojo.getJoinedDate());

                    for (MagicWandPojo magicWandPojo : responseMagicWand.getBody()) {

                        log.info(String.valueOf(order.getMagicWandID()));
                        log.info(String.valueOf(magicWandPojo.getMagicWandID()));

                        if (magicWandPojo.getMagicWandID() == order.getMagicWandID()) {
                            if (order.getWizardAge() < magicWandPojo.getAgeLimit()) {
                                if (magicWandPojo.getWandStock() > 0) {
                                    order.setMagicWandID(magicWandPojo.getMagicWandID());
                                    order.setMagicWandName(magicWandPojo.getMagicWandName());
                                    order.setMagicWandDesc(magicWandPojo.getMagicWandDesc());

                                    magicWandPojo.setWandStock(magicWandPojo.getWandStock() - 1);

                                    HttpHeaders headers = new HttpHeaders();
                                    headers.setContentType(MediaType.APPLICATION_JSON);
                                    HttpEntity<MagicWandPojo> request = new HttpEntity<>(magicWandPojo, headers);

                                    restTemplate.exchange(GET_WAND_UPDATE_API, HttpMethod.PUT, request, MagicWandPojo.class);


                                    orderRepository.save(order);
                                    log.info("Order created");
                                    return "Order created";
                                }
                                log.info("Magic Wand ID: " + magicWandPojo.getMagicWandID() + "\nName: " + magicWandPojo.getMagicWandName() +
                                        "\nis out of stock");
                                return "Magic Wand ID: " + magicWandPojo.getMagicWandID() + "\nName: " + magicWandPojo.getMagicWandName() +
                                        "\nis out of stock";

                            }
                            log.info(order.getWizardName() + " age is: " + order.getWizardAge() + ". The age is exceeds the age limit." +
                                    "\nAge limit: " + magicWandPojo.getAgeLimit());
                            return order.getWizardName() + " age is: " + order.getWizardAge() + ". The age is exceeds the age limit." +
                                    "\nAge limit: " + magicWandPojo.getAgeLimit();
                        }

                    }
                    log.info("Invalid Magic Wand ID");
                    return "Invalid Magic Wand ID";
                }
                log.info("Wizard with ID: " + wizardPojo.getWizardID() + " is not active");
                return "Wizard with ID: " + wizardPojo.getWizardID() + " is not active";
            }
        }
        log.info("Invalid Wizard ID");
        return "Invalid Wizard ID";
    }

    public Order updateOrder(Order order) {
        log.info("Updating order in repository.");
        return orderRepository.save(order);
    }

    public void deleteOrder(int orderID) {
        log.info("Deleting Order with ID: " + orderID + " from repository.");
        orderRepository.findById(orderID).orElseThrow(
                () -> {
                    log.severe("Error deleting Order with ID " + orderID + ": " + "Does not exist");
                    return new OrderException(OrderException.ID_DOES_NOT_EXIST);
                }
        );
        orderRepository.deleteById(orderID);
    }
}
