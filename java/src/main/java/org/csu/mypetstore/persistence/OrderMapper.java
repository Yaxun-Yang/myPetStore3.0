package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {


    List<Order> getOrdersByUsername(String username);

    void insertOrder(Order order);

    Order getRecentOrder(String username);

    String  getOrderId(String username);

    void updateOrder(Order order);

    Order getOrderByOrderId(String orderId);

    void deleteOrder(String orderId);

    void deleteOrders(String username);


}
