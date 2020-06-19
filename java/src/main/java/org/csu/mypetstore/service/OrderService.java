package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.CartItemMapper;
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

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ItemMapper itemMapper;

    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    public void insertLineItem(LineItem lineItem)
    {
        lineItemMapper.insertLineItem(lineItem);
    }

    public Order getOrder(String orderId){
        return orderMapper.getOrderByOrderId(orderId);
    }

    public Order getRecentOrder(String username)
    {
        return orderMapper.getRecentOrder(username);

    }

    public List<Order> getOrdersByUsername(String username) {
        return orderMapper.getOrdersByUsername(username);
    }

    public String getOrderId(String username)
    {
        return orderMapper.getOrderId(username);
    }

    public List<LineItem> getLineItemList(String orderId)
    {
        return  lineItemMapper.getLineItemsByOrderId(orderId);
    }

    public int getTotalCount(String orderId)
    {
        return lineItemMapper.getTotalCount(orderId);
    }

    public float getSubTotal(String orderId)
    {
        return lineItemMapper.getSubTotal(orderId);
    }

    @Transactional
    public void checkout(String orderId)
    {
        Order order = orderMapper.getOrderByOrderId(orderId);
        order.setCheckout("Y");
        orderMapper.updateOrder(order);

        List<LineItem> lineItemList = lineItemMapper.getLineItemsByOrderId(orderId);
        for(int i=0; i<lineItemList.size(); i++)
        {
            String itemId =lineItemList.get(i).getItemId();
            cartItemMapper.deleteCartItem(order.getUsername(), itemId);
            itemMapper.updateQuantity(itemId, itemMapper.getQuantity(itemId)-lineItemList.get(i).getQuantity());
        }


    }

    public void paid(String orderId)
    {
        Order order  =orderMapper.getOrderByOrderId(orderId);
        order.setPaid("Y");
        orderMapper.updateOrder(order);
    }

    public void dispatched(String orderId)
    {
        Order order = orderMapper.getOrderByOrderId(orderId);
        order.setDispatched("Y");
        orderMapper.updateOrder(order);
    }

    public void updateOder(Order order)
    {
        orderMapper.updateOrder(order);
    }

    @Transactional
    public void deleteOrder(String orderId )
    {
        orderMapper.deleteOrder(orderId);
        lineItemMapper.deleteOrder(orderId);
    }

    @Transactional
    public void deleteOrders(String username)
    {
        List<Order> orderList = orderMapper.getOrdersByUsername(username);
        for(int i =0; i<orderList.size(); i++)
        {
            String orderId = orderList.get(i).getOrderId();
            lineItemMapper.deleteOrder(orderId);
        }
        orderMapper.deleteOrders(username);
    }


}