package org.csu.mypetstore.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderApi {

    @Autowired
    OrderService orderService;

    @GetMapping("/orderList/{username}")
    public ResponseTemplate getOrderList(@PathVariable String username)
    {
        JSONObject data = new JSONObject();

        data.put("orderList", orderService.getOrdersByUsername(username));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/order/{username}")
    public ResponseTemplate getRecentOrder(@PathVariable String username)
    {
        JSONObject data = new JSONObject();


        data.put("order", orderService.getRecentOrder(username));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @PostMapping("/order/{username}")
    public ResponseTemplate creatNewOrder(@PathVariable String username, Order order)
    {
        order.setOrderDate(new Date());
        order.setPaid(false);
        order.setUsername(username);
        orderService.insertOrder(order);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/orderId/{username}")
    public ResponseTemplate getOrderId(@PathVariable String username)
    {
        JSONObject data = new JSONObject();

        data.put("orderId", orderService.getOrderId(username));
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @PutMapping("/paid/{orderId}")
    public ResponseTemplate paid(@PathVariable int orderId)
    {

        orderService.paid(orderId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/lineItemList/{orderId}")
    public ResponseTemplate insertLineItemList(@PathVariable int orderId, List<LineItem> lineItemList)
    {
        for(int i=0;i<lineItemList.size();i++)
        {
            lineItemList.get(i).setOrderId(orderId);
            orderService.insertLineItem(lineItemList.get(i));
        }

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/lineItem/{orderId}")
    public ResponseTemplate insertLineItem(@PathVariable int orderId, LineItem lineItem)
    {
        lineItem.setOrderId(orderId);
        orderService.insertLineItem(lineItem);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }
}
