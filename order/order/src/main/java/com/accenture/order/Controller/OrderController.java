package com.accenture.order.Controller;

import com.accenture.order.Entity.*;
import com.accenture.order.Repository.*;
import com.accenture.order.Service.*;
import org.springframework.beans.factory.annotation.*;
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

    @GetMapping("getOrder")
    public List<Order> getOrder(){
        return service.getOrder();
    }

    @PostMapping("add")
    public String addOrder(@RequestBody Order order){
        return service.addOrder(order);
    }

    @PutMapping("update")
    public Order updateOrder(@RequestBody Order order){
        return service.updateOrder(order);
    }

    @DeleteMapping("delete/{orderID}")
    public String deleteOrder(@PathVariable(value = "orderID") int orderID){
        service.deleteOrder(orderID);
        return ("Order with ID " + orderID + " is deleted");
    }


}
