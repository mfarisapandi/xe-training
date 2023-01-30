package com.accenture.order.Service;

import com.accenture.order.Entity.*;
import com.accenture.order.Repository.*;
import org.springframework.beans.factory.annotation.*;
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

    public List<Order> getOrder(){
        List<Order> order = orderRepository.findAll();
        return order;
    }

    public ResponseEntity<Order> addOrder(Order order){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Order> request = new HttpEntity<>(order, headers);
        ResponseEntity<Order> response = restTemplate.exchange("http://localhost:8090/api/xe/wizardList", HttpMethod.POST, request, Order.class);
        return response;
    }
}
