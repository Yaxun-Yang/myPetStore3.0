package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.ItemMapper;
import org.csu.mypetstore.persistence.LineItemMapper;
import org.csu.mypetstore.persistence.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private LineItemMapper lineItemMapper;

    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    public Order getRecentOrder(String username)
    {
        return orderMapper.getRecentOrder(username);

    }

    public List<Order> getOrdersByUsername(String username) {
        return orderMapper.getOrdersByUsername(username);
    }

    public int getOrderId(String username)
    {
        return orderMapper.getOrderId(username);
    }

    public void paid(int orderId )
    {
        orderMapper.updatePaid(orderId, true);
    }

    public void insertLineItemList (List <LineItem> lineItemList)
    {
        for(int i=0; i<lineItemList.size();i++)
        {
            lineItemMapper.insertLineItem(lineItemList.get(i));
        }
    }

    public void insertLineItem(LineItem lineItem)
    {
        lineItemMapper.insertLineItem(lineItem);
    }

}