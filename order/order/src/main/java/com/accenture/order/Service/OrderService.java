package com.accenture.order.Service;

import com.accenture.order.Entity.*;
import com.accenture.order.Repository.*;
import org.springframework.beans.factory.annotation.*;
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
    public Order addOrder(Order order) {
        LOGGER.info("Adding order to repository.");
        return orderRepository.save(order);
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
