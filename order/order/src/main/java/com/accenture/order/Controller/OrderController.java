package com.accenture.order.Controller;

import com.accenture.order.Entity.*;
import com.accenture.order.Repository.*;
import com.accenture.order.Service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/xe/")
public class OrderController {

    @Autowired
    OrderService service;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("")
    public String intro(){
        return "Welcome!";
    }

    @GetMapping("allList")
    public String allList(){
        return service.getList();
    }

    @GetMapping("order")
    public List<Order> getOrder(){
        return service.getOrder();
    }

    @PutMapping("add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        return service.addOrder(order);
    }


}
