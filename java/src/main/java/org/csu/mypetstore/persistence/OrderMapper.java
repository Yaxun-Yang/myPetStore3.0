package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {


    List<Order> getOrdersByUsername(String username);

    void insertOrder(Order order);

    Order getRecentOrder(String username);

    int getOrderId(String username);

    void updatePaid (int orderId, boolean paid);
}
