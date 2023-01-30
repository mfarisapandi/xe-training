package com.accenture.order.Repository;

import com.accenture.order.Entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByOrderID(Integer orderID);
}
